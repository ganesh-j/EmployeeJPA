package com.emp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.emp.model.Employee;
import com.emp.repo.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeControllerMvcTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private EmployeeRepository repo;
	
	@Test
	public void testfindAll() throws Exception {
		
		Employee emp = new Employee();
		emp.setId(101);
		emp.setDesignation("Engineer");
		emp.setName("Test");
		emp.setSalary((double) 1000);
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(emp);
		when(repo.findAll()).thenReturn(emps);
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mock.perform(get("/emp/list")).andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(emps)));
					//"[{'id':101,'name':'Test','designation':'Engineer','salary':1000}]"));
		
	}
	
	
	@Test
	public void testDeleteEmployee() throws Exception {
		
		doNothing().when(repo).deleteById(102);
		
		mock.perform(delete("/emp/101")).andExpect(status().isOk());
		
		
	}
	

}
