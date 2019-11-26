# run "terraform init" after clone to get all required modules
- This terraform code does following things
  - creates vpc in aws virginia region
  - 2 subnets : Private and Public
  - 2 Route Tables
  - Nat gatway attached to Private Subnet and IGW to Public Gatway
  - Launch first machine into Public Subnet and install ansible, java and jenkins
  - Attach public ip to first machine
  - Launch second machine into private subnet
