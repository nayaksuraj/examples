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
		// WRITE CODE HERE
		
	}
	
	
	@Test
	public void testAddingEmployee() {
		// WRITE CODE HERE
	}

}
