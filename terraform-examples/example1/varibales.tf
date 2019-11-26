
# environment prod, dev, staging or qa
variable "env" {
    description = "Environment"
    default = "Dev"
}

# aws region name
variable "aws_region_name" {
  description = "Region Name for the ${var.env} VPC"
  default     = "Virginia"
}

# aws region
variable "aws_region" {
  description = "Region for the ${var.env} VPC"
  default     = "us-east-1"
}

/*
  vpc related variables
*/

# vpc cidr
variable "aws_vpc_cidr" {
  description = "CIDR for the ${env} VPC"
  default     = "170.30.0.0/16"
}

# subnets - Private
# public subnet
variable "public_subnet_cidr_az1" {
  description = "cidr for public subnet"
  default     = "170.30.4.0/22"
}

# private subnet
variable "private_subnet_cidr_az2" {
  description = "cidr for private subnet"
  default     = "170.30.8.0/22"
}

# aws ssh key Name
variable "aws_ssh_key_name" {
  description = "ssh key name"
  default = "deployer-key"
}
