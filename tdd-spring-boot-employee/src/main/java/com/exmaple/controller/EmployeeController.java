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
		employee = employeeService.addEmployee(employee);
		return employee;
	}
	
	@GetMapping("/employees")
	public List<Employee> showEmployees(){
		System.out.println("Employee listing called");
		List<Employee> employees = employeeService.listEmployees();
		System.out.println("Employee listing returninig " + employees);
		return employees;
	}
	
	@PostMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employee = employeeService.updateEmployee(employee);
		return employee;
	}
	
}
