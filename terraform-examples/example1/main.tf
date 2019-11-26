# create vpc
# launch machine in vpc (ci machine)
# configure jenkins, ansible in launched machine
# launch machine in vpc (dev machine)

terraform {
  required_version = "0.12.9"
}

provider "aws" {
  region                  = var.aws_region
  version                 = "2.35.0"
}


# Define the common tags for all resources
locals {
  common_tags = {
    Terraform   = "true"
    Environment = var.env
  }
}

# create vpc
resource "aws_vpc" "default" {
  cidr_block           = var.aws_vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-${var.aws_region_name}-Vpc"
    )
  )
}

# create internet gatway
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.default.id

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-IGW-${var.aws_region_name}"
    )
  )
}


# create subnets -> Public Subnet
resource "aws_subnet" "public_subnet_cidr" {
  vpc_id            = aws_vpc.default.id
  cidr_block        = var.public_subnet_cidr_az1
  availability_zone = "us-east-1a"

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-Public-Subnet"
    )
  )
}

# create subnets -> Private Subnet
resource "aws_subnet" "private_subnet_cidr" {
  vpc_id            = aws_vpc.default.id
  cidr_block        = var.private_subnet_cidr_az2
  availability_zone = "us-east-1b"

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-Private-Subnet"
    )
  )
}

# create nat gateway
# before that create nat eip
resource "aws_eip" "nat_eip" {
  vpc      = true
  depends_on = ["aws_internet_gateway.gw"]
}

# Define the nat gateway
resource "aws_nat_gateway" "nat_gw" {
  allocation_id = aws_eip.nat_eip.id
  subnet_id     = aws_subnet.public_subnet_cidr.id

  depends_on = ["aws_internet_gateway.gw"]

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-NAT-GW"
    )
  )
}

# create route tables
resource "aws_route_table" "public-route" {
  vpc_id = aws_vpc.default.id

  # add nat gateway to db route table
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-Public-Route"
    )
  )
}

resource "aws_route_table" "private-route" {
  vpc_id = aws_vpc.default.id

  # add nat gateway to db route table
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_nat_gateway.nat_gw.id
  }

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-Private-Route"
    )
  )
}

# associate subnets with route table
resource "aws_route_table_association" "private-route-table" {
  subnet_id      = aws_subnet.private_subnet_cidr.id
  route_table_id = aws_route_table.private-route.id
}

resource "aws_route_table_association" "public-route-table" {
  subnet_id      = aws_subnet.public_subnet_cidr.id
  route_table_id = aws_route_table.public-route.id
}


/*
  create security group according to subnets.
  here we have created 2 subnets public and private, so we are creating 2 security
  groups also. one sg will allow port 80,443 and 22 from anywhere
  second will only allow 22 to first sg
*/

resource "aws_security_group" "public_sg_1" {
  name        = "${var.env}-Public-SG"
  description = "Allow access to port 22"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    self        = true
    description = "Allow access on port 22 to self"
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "allow access on port 22 to all"
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "allow access on port 8080 to all"
  }

  egress {
    from_port       = 0
    to_port         = 0
    protocol        = "-1"
    cidr_blocks     = ["0.0.0.0/0"]
  }

  vpc_id = aws_vpc.default.id

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-Public-SG"
    )
  )
}

resource "aws_security_group" "private_sg_2" {
  name        = "${var.env}-Private-SG"
  description = "Allow access to port 22"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    self        = true
    description = "Allow access on port 22 to self"
  }

  egress {
    from_port       = 0
    to_port         = 0
    protocol        = "-1"
    cidr_blocks     = ["0.0.0.0/0"]
  }

  vpc_id = aws_vpc.default.id

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-Private-SG"
    )
  )
}


# allow on port 22 for public sg into private sg
resource "aws_security_group_rule" "add_public_sg" {
  type                     = "ingress"
  from_port                = 22
  to_port                  = 22
  protocol                 = "tcp"
  source_security_group_id = aws_security_group.public_sg_1.id
  description              = "Allow access to port 22 to public sg"

  security_group_id = aws_security_group.private_sg_2.id
}

/*
# launch one machine in public subnet and second in private subnet
# befor that create ssh key pair so we can access machine later via ssh
# Note: We need to generate ssh private and public key. see the below link to create ssh keys
# https://www.ssh.com/ssh/keygen/
*/

resource "aws_key_pair" "ssh_key" {
  key_name   = var.aws_ssh_key_name
  public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDzrtA/YtD0XdGq+emBNUxCPOfutw46k9+CKoa2KqlXdKjjGDdvNeP+jnYD9Fl4Re4sBUWgVAY4AlazkICtmLA50YHhVTY8h9ugC9e0s9g2Px3SXgrYOdxL7fBWTXq7vllZl1rcmrmp/+vlJH+rtu0vgnQU8ZHXTAKeM1fh2rYOF0KkSyYyvl21RdY1SRzzBojXc+qgZw2kWuOgzI7vILDZxehfB6NjDrDyP4oV45X9RZI3QUMJ7XUogdLIYUV2ujZr/I7Rqwj4N7HLhIzBN2wgnlIhHJ3j6fL6S8VmHEANpa1ZCxgmgM9zELxoRmW0QYpLvpAckwbC4BKRIrV3pcNr"
}

# create ci machine
resource "aws_instance" "ci" {
  ami           = "ami-0ff8a91507f77f867"
  instance_type = "t2.micro"
  subnet_id  = aws_subnet.public_subnet_cidr.id
  key_name = var.aws_ssh_key_name
  disable_api_termination = "false"

  vpc_security_group_ids = [aws_security_group.public_sg_1.id]

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-CI-Machine"
    )
  )

  # configure machine using user-data
  user_data = <<-EOF
              #!/bin/bash
              sudo yum update -y
              sudo pip install --upgrade pip
              pip_path=`whereis pip | awk '{print $3}'`
              sudo ln -s `echo $pip_path` /usr/bin/pip
              sudo yum remove java-1.7.0-openjdk -y
              sudo curl --silent --location http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo | sudo tee /etc/yum.repos.d/jenkins.repo
              sudo rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key
              sudo yum install java-1.8.0-openjdk-devel -y
              sudo pip install ansible
              sudo yum install jenkins -y
              sudo /etc/init.d/jenkins start
              sudo chkconfig jenkins on
              EOF
}

# allocate and associate public ip to newly launched machine
resource "aws_eip" "ci_machine_eip" {
  instance = aws_instance.ci.id
  vpc      = true
}

# @TODO - Need to work on file copy from local machine to remote machine
# resource "null_resource" "copy-test-file" {
#
#   provisioner "file" {
#     source      = "/root/.ssh/localvm"
#     destination = "/tmp/test.txt"
#
#     connection {
#       type     = "ssh"
#       host     = "${element(aws_instance.ci.*.public_ip, 0)}"
#       private_key = "/root/.ssh/localvm"
#       user     = "ec2-user"
#     }
#   }
# }

# create instance
resource "aws_instance" "dev_machine" {
  ami           = "ami-0ff8a91507f77f867"
  instance_type = "t2.micro"
  subnet_id  = aws_subnet.private_subnet_cidr.id
  key_name = var.aws_ssh_key_name
  disable_api_termination = "false"

  vpc_security_group_ids = [aws_security_group.private_sg_2.id]

  tags = merge(
    local.common_tags,
    map(
      "Name", "${var.env}-DEV-Machine"
    )
  )

  # configure machine using user-data
  user_data = <<-EOF
              #!/bin/bash
              sudo yum update -y
              sudo yum remove java-1.7.0-openjdk -y
              sudo yum install java-1.8.0-openjdk-devel -y
              EOF
}
