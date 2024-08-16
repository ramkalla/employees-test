package com.employees.test.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.employees.test.model.ResponsedataModel;

import io.micrometer.common.util.StringUtils;

@Component
public class ResponseUtility {

	private final String SUCCESS_MESSAGE = "Success";
	private final String FAILURE_MESSAGE = "Failure";
	private final String NOT_FOUND_MESSAGE = "Data not found";

	public ResponseEntity<ResponsedataModel> buildSuccessResponse(Object data) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.OK);
		responseModel.setMessage(SUCCESS_MESSAGE);
		responseModel.setErrorcode(null);
		responseModel.setData(data);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.OK);
	}

	public ResponseEntity<ResponsedataModel> badRequest(Object data, String message) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.BAD_REQUEST);
		responseModel.setMessage(message);
		responseModel.setErrorcode(null);
		responseModel.setData(data);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<ResponsedataModel> buildException(Object data, Exception e) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR);
		responseModel.setMessage(e.getMessage());
		responseModel.setErrorcode(null);
		responseModel.setData(data);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<ResponsedataModel> buildSuccessResponseCreated(Object data) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.CREATED);
		responseModel.setMessage(SUCCESS_MESSAGE);
		responseModel.setErrorcode(null);
		responseModel.setData(data);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponsedataModel> dataNotFound(Object data) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.NOT_FOUND);

		responseModel.setMessage(NOT_FOUND_MESSAGE);

		responseModel.setErrorcode(null);
		responseModel.setData(data);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponsedataModel> dataNotFound(Object data, String message) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.NOT_FOUND);
		if (!StringUtils.isEmpty(message)) {
			responseModel.setMessage(message);
		} else {
			responseModel.setMessage(NOT_FOUND_MESSAGE);
		}

		responseModel.setErrorcode(null);
		responseModel.setData(data);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponsedataModel> dataNotFound(String message) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.NOT_FOUND);
		if (!StringUtils.isEmpty(message)) {
			responseModel.setMessage(message);
		} else {
			responseModel.setMessage(NOT_FOUND_MESSAGE);
		}

		responseModel.setErrorcode(null);
		responseModel.setData(null);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.NOT_FOUND);
	}

	public ResponseUtility() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseEntity<ResponsedataModel> buildException(String message) {
		ResponsedataModel responseModel = new ResponsedataModel();
		responseModel.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR);
		responseModel.setMessage(message);
		responseModel.setErrorcode(null);
		responseModel.setData(null);
		return new ResponseEntity<ResponsedataModel>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
