package com.emp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emp.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class EmployeeExceptionHandler {
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
	

}
