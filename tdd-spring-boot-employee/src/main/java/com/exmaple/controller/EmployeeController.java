package com.exmaple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exmaple.model.Employee;
import com.exmaple.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PutMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee){
		// WRITE CODE HERE
		return employee;
	}
	
	@GetMapping("/employees")
	public List<Employee> showEmployees(){
		// WRITE CODE HERE
		return employees;
	}
	
	@PostMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		// WRITE CODE HERE
		return employee;
	}
	
}
