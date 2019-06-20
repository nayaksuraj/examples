package com.exmaple.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty(value="employeeId", required=false)
	private Long employeeId;
	@JsonProperty(value="firstname")
	private String firstName;
	@JsonProperty(value="lastname")
	private String lastName;
	@JsonProperty(value="salary")
	private Integer salary;
	@JsonProperty(value="department")
	private String department;
	
	public Employee() {}
	
	public Employee(Long employeeId, String firstName, String lastName, Integer salary, String department) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.department = department;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
}
