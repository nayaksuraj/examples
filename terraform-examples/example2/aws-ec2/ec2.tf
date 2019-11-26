# Define the common tags for all resources
locals {
  common_tags = {
    Terraform   = "true"
    Environment = var.env
  }
}

data "terraform_remote_state" "aws_vpc" {
 backend = "s3"

  config = {
    bucket = "tf-bucket-statefile"
    key = "aws-vpc/terraform.tfstate"
    region = "us-east-1"
  }
}


data terraform_remote_state "aws_elb" {
   backend = "s3"

  config = {
    bucket = "tf-bucket-statefile"
    key = "aws-vpc/terraform.tfstate"
    region = "us-east-1"
  }
}

resource "aws_instance" "ec2_instance" {
  count = 2
  ami = "ami-00dc79254d0461090"
  instance_type = "t2.micro"
  subnet_id = data.terraform_remote_state.aws_vpc.outputs.public_subnet_id
  tags = merge(local.common_tags, map("Name", "ec2-${var.aws_region}"))
}
