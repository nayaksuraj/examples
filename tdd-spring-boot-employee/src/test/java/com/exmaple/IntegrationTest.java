/**
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exmaple.persistencemodel.DepartmentEntity;
import com.exmaple.persistencemodel.DepartmentRepository;
import com.exmaple.persistencemodel.EmployeeEntity;
import com.exmaple.persistencemodel.EmployeeRepository;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = JpaPrjApplication.class
)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application.properties")
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testEmployeeListing() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/employee").accept(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

}
*/
