package com.employees.test.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RepairOrderException extends RuntimeException{

	String message;
	HttpStatus statusCode;
	
	public RepairOrderException(String message,HttpStatus statusCode) {
		super(message);
		this.message=message;
		this.statusCode=statusCode;
	}
}
