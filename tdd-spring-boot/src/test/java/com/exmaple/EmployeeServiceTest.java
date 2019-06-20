package com.exmaple;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.exmaple.model.Employee;
import com.exmaple.persistencemodel.DepartmentEntity;
import com.exmaple.persistencemodel.DepartmentRepository;
import com.exmaple.persistencemodel.EmployeeEntity;
import com.exmaple.persistencemodel.EmployeeRepository;
import com.exmaple.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	DepartmentRepository departmentRepository;
	
	@InjectMocks
	EmployeeService employeeService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmployeeListing() {
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		employees.add(
				new EmployeeEntity(123123L, "Suraj", "Nayak", 300, new DepartmentEntity(12321L,"IT", "IT101"))
				);
		Mockito.when(employeeRepository.findAll()).thenReturn(employees);
		
		List<Employee> employes = employeeService.listEmployees();
		assertThat(employes).isNotEmpty();
		assertThat(employes).hasSize(1);
		assertThat(employes.get(0).getFirstName()).isEqualTo("Suraj");
		
	}
	
	
	@Test
	public void testAddingEmployee() {
		Employee newEmployee = new Employee(null, "test123", "last123", 3000, "IT");
		
		Mockito.when(departmentRepository.findFirst1ByName(newEmployee.getDepartment()))
			.thenReturn(new DepartmentEntity(1212L, newEmployee.getDepartment(), "IT101"));
		
		Mockito.when(employeeRepository.save(ArgumentMatchers.any(EmployeeEntity.class)))
		.thenReturn(new EmployeeEntity(123123L, "test123", "last123", 3000, new DepartmentEntity(1212L,"IT", "IT101")));
		
		Employee savedEmployee = employeeService.addEmployee(newEmployee);
		System.out.println(savedEmployee);
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getEmployeeId()).isNotNull();
//		assertThat(employeeRepository.findByFirstName("test123")).isNotNull();
	}

}
