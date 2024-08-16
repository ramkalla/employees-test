package com.employees.test.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) {
		ErrorDetail var = new ErrorDetail(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(var, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetail> handleUserNotFoundException(Exception ex, WebRequest request) {
		ErrorDetail var = new ErrorDetail(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(var, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingHeaders.class)
	public final ResponseEntity<ErrorDetail> missingHeaderException(Exception ex, WebRequest request) {
		ErrorDetail var = new ErrorDetail(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(var, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RepairOrderException.class)
	public final ResponseEntity<ErrorDetail> handleBadRequestException(Exception ex, WebRequest request) {
		ErrorDetail var = new ErrorDetail(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(var, HttpStatus.BAD_REQUEST);
	}

}
