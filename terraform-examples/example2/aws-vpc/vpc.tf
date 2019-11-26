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

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}

# create internet gatway
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.default.id

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}


# create subnets -> Public Subnet
resource "aws_subnet" "public_subnet_cidr" {
  vpc_id            = aws_vpc.default.id
  cidr_block        = var.public_subnet_cidr_az1
  availability_zone = "us-east-1a"

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}

# create subnets -> Private Subnet
resource "aws_subnet" "private_subnet_cidr" {
  vpc_id            = aws_vpc.default.id
  cidr_block        = var.private_subnet_cidr_az2
  availability_zone = "us-east-1b"

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
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

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}

# create route tables
resource "aws_route_table" "public-route" {
  vpc_id = aws_vpc.default.id

  # add internet gateway
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}

resource "aws_route_table" "private-route" {
  vpc_id = aws_vpc.default.id

  # add nat gateway to db route table
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_nat_gateway.nat_gw.id
  }

  tags = merge(local.common_tags, map("Name", "${var.env}-Private-Route"))
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


resource "aws_security_group" "public_sg_1" {
  name        = "${var.env}-Public-SG"
  description = "Allow access to port 22"

  vpc_id = aws_vpc.default.id

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}

resource "aws_security_group" "private_sg_2" {
  name        = "${var.env}-Private-SG"

  vpc_id = aws_vpc.default.id

  tags = merge(local.common_tags, map("Name", "${var.env}-Public-Route"))
}

resource "aws_security_group_rule" "ingress_rule_1" {
  from_port = 22
  protocol = "tcp"
  security_group_id = aws_security_group.public_sg_1.id
  to_port = 22
  type = "ingress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "egress_rule_2" {
  from_port = 0
  protocol = "all"
  security_group_id = aws_security_group.public_sg_1.id
  to_port = -1
  type = "egress"
  cidr_blocks = ["0.0.0.0/0"]
}
resource "aws_security_group_rule" "ingress_rule_3" {
  protocol = "all"
  security_group_id = aws_security_group.public_sg_1.id
  from_port = 0
  to_port = -1
  type = "ingress"
  source_security_group_id = aws_security_group.public_sg_1.id
}




resource "aws_security_group_rule" "ingress_rule_4" {
  protocol = "all"
  security_group_id = aws_security_group.private_sg_2.id
  from_port = 0
  to_port = -1
  type = "ingress"
  source_security_group_id = aws_security_group.public_sg_1.id
}

resource "aws_security_group_rule" "ingress_rule_5" {
  protocol = "all"
  security_group_id = aws_security_group.private_sg_2.id
  from_port = 0
  to_port = -1
  type = "ingress"
  source_security_group_id = aws_security_group.private_sg_2.id
}

