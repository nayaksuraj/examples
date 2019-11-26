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

data "terraform_remote_state" "aws_ec2" {
  backend = "s3"

  config = {
    bucket = "tf-bucket-statefile"
    key = "aws-ec2/terraform.tfstate"
    region = "us-east-1"
  }
}

data "aws_subnet_ids" "aws_subnet_ids" {
  vpc_id = data.terraform_remote_state.aws_vpc.outputs.vpc_id
}

resource "aws_elb" "elb" {

  name = var.elb_name

  subnets = data.aws_subnet_ids.aws_subnet_ids.ids

  listener {
    instance_port = 80
    instance_protocol = "http"
    lb_port = 80
    lb_protocol = "http"
  }

  health_check {
    healthy_threshold = 2
    interval = 30
    target = "HTTP:80/"
    timeout = 3
    unhealthy_threshold = 2
  }

  connection_draining = true
  connection_draining_timeout = 400
  idle_timeout = 400

  tags = merge(local.common_tags, map("Name", "elb-${var.aws_region}"))
}

resource "aws_elb_attachment" "elb_ec2_attach" {
  count = length(data.terraform_remote_state.aws_ec2.outputs.ec2_instance)
  elb = aws_elb.elb.name
  instance = data.terraform_remote_state.aws_ec2.outputs.ec2_instance[count.index]
}