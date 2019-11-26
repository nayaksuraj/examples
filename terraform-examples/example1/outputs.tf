output "aws_instance_id" {
  value = aws_instance.ci.id
}

output "aws_public_ip" {
  value = aws_eip.ci_machine_eip.public_ip
}