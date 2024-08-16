package com.employees.test.model;

import java.io.Serializable;

public class ResponsedataModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String errorcode;
	private Object statuscode;
	private Object data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public Object getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(Object statuscode) {
		this.statuscode = statuscode;
	}

	public ResponsedataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
