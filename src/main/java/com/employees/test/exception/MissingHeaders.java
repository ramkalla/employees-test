package com.employees.test.exception;


public class MissingHeaders extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public MissingHeaders(String message) {
		super(message);
	}
}
