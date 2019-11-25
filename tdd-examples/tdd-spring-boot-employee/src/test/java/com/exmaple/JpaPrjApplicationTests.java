package com.exmaple;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exmaple.persistencemodel.DepartmentEntity;
import com.exmaple.persistencemodel.DepartmentRepository;
import com.exmaple.persistencemodel.EmployeeEntity;
import com.exmaple.persistencemodel.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JpaPrjApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	
	@Before
	public void setUp() throws Exception {
		DepartmentEntity departmentEntity = departmentRepository.save(new DepartmentEntity(12312L, "IT", "IT101"));
		System.out.println("Saved Employee " + employeeRepository.save(new EmployeeEntity(123123L, "Ashish", "Nanotakar", 3000, departmentEntity)));
	}

	@Test
	public void testEmployeeListing() throws Exception {
		
		mockMvc.perform(get("/employees")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstname", is("Ashish")));

	}

}
