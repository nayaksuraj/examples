terraform {
  required_version = "0.12.9"
  backend "s3" {
    bucket = "tf-bucket-statefile"
    key = "aws-vpc/terraform.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  region        = "us-east-1"
  version       = "2.35.0"
}