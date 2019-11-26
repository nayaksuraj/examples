
# environment prod, dev, staging or qa
variable "env" {
  description = "Environment"
  default = "Dev"
}

# aws region name
variable "aws_region_name" {
  description = "Region Name for theVPC"
  default     = "Virginia"
}

# aws region
variable "aws_region" {
  description = "Region for the VPC"
  default     = "us-east-1"
}


# aws ssh key Name
variable "aws_ssh_key_name" {
  description = "ssh key name"
  default = "deployer-key"
}

variable "elb_name" {
  default = "aws-test-elb"
}