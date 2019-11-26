output "ec2_instance" {
  value = aws_instance.ec2_instance.*.id
}