package com.exmaple.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exmaple.model.Employee;
import com.exmaple.persistencemodel.DepartmentEntity;
import com.exmaple.persistencemodel.DepartmentRepository;
import com.exmaple.persistencemodel.EmployeeEntity;
import com.exmaple.persistencemodel.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRespository;
	
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRespository = employeeRepository;
		this.departmentRepository = departmentRepository;
	} 
	
	public Employee addEmployee(Employee employee) {
		
		DepartmentEntity departmentEntity = departmentRepository.findFirst1ByName(employee.getDepartment());
		if(departmentEntity == null) {
			System.out.println("Department is blank");
			return null;
		}
		
		EmployeeEntity employeeEntity = convertEmployeeToEmployeeEntity(employee);
		employeeEntity.setDepartment(departmentEntity);
		
		employeeEntity = employeeRespository.save(employeeEntity);
		if(employeeEntity == null) {
			System.out.println("Employee Entity is blank ");
			return null;
		}
		employee = convertEmployeeEntityToEmployee(employeeEntity);
		
		return employee;
	}

	private Employee convertEmployeeEntityToEmployee(EmployeeEntity employeeEntity) {
		return new Employee(
				employeeEntity.getId(),
				employeeEntity.getFirstName(),
				employeeEntity.getLastName(),
				employeeEntity.getSalary(),
				employeeEntity.getDepartment().getName()
		);
	}

	private EmployeeEntity convertEmployeeToEmployeeEntity(Employee employee) {
		return new EmployeeEntity(
				employee.getEmployeeId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getSalary(),
				null
		);
	}

	public List<Employee> listEmployees() {
		List<EmployeeEntity> employeeEntities = employeeRespository.findAll(); 
		if(employeeEntities.isEmpty()) {
			return new ArrayList<Employee>(0);
		}
		
		return employeeEntities
			.stream()
			.map(entity -> convertEmployeeEntityToEmployee(entity))
			.collect(Collectors.toList());
	}

	public Employee updateEmployee(Employee employee) {
		if (employee.getEmployeeId() == null) {
			return null;
		}
		EmployeeEntity employeeEntity = employeeRespository.save(convertEmployeeToEmployeeEntity(employee));
		return convertEmployeeEntityToEmployee(employeeEntity);
	}
 }
