package com.employees.test.exception;


import java.time.LocalDate;

public class ErrorDetail {
	private LocalDate timeStamp;
	private String message;
	private String details;
	private String errorcode;
	private Object data;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ErrorDetail(LocalDate timeStamp, String message, String details) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public ErrorDetail(LocalDate timeStamp, String message, String details, String ErrorCode) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
