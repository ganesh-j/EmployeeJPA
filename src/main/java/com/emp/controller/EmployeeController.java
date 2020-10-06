	package com.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emp.exception.EmployeeNotFoundException;
import com.emp.model.Employee;
import com.emp.repo.EmployeeRepository;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository repo;
	
	
	// creating emp
	@PostMapping("/create")
	public String createEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		return "ok";
	}
	
	@GetMapping(value="/list")
	public List<Employee> getEmployees(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		log.info("Getting the employee details for id : " +id);
		log.debug("debug level :  id " + id);
		
		Employee e = null;
		try {
			e = repo.findById(id).get();
		} catch (Exception e1) {
			log.error(" Error occured while fetching employee deatils : " + e1.toString());
			e1.printStackTrace();
			throw new EmployeeNotFoundException("Employee not found");
			
		}
		return e;
	}
	
	
	@PutMapping("/update")
	public String updateEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		return "updated";
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		
		repo.deleteById(id);
		return "deleted";
	}
	
	

}
