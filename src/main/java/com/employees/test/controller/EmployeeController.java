package com.employees.test.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employees.test.entities.Employee;
import com.employees.test.exception.BusinessException;
import com.employees.test.model.ResponsedataModel;
import com.employees.test.service.EmployeeService;
import com.employees.test.util.APIConstants;
import com.employees.test.util.ResponseUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(APIConstants.EMPLOYEE_PATH)
@Tag(name = "Employees", description = "Employee Retrival APIS based on employee id or emailid")
public class EmployeeController {

	public static Logger log = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	public EmployeeService employeeService;

	@Operation(summary = "Get Employee by id", description = "Get employee filtered by the provided employee id", tags = {
			"Employee Retrival APIS based on employee id ", "get" })
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ResponsedataModel.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = {
					@Content(schema = @Schema(implementation = ResponsedataModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "500", content = {
					@Content(schema = @Schema(implementation = ResponsedataModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping(APIConstants.EMPLOYEE_API)
	@Parameter(name = "empID", description = "empID is required", required = true, in = ParameterIn.PATH)
	@Parameter(name = "email", description = "empEmail is required", required = false, in = ParameterIn.QUERY)
	public ResponseEntity<ResponsedataModel> getEmployees(
			@PathVariable(value = "empID", required = true) String employeeID,
			@RequestParam(value = "email", required = false) String email
			) throws InterruptedException {
		try {
			log.info("employee id ==> " + StringEscapeUtils.escapeJava(employeeID));
			log.info("employee email ==> " + StringEscapeUtils.escapeJava(email));

			Optional<List<Employee>> employeeData;
			if (StringUtils.isEmpty(email)) {
				Optional<Employee> emp = employeeService.getEmployeeByID(Integer.valueOf(employeeID));
				if (emp.isPresent()) {
					return new ResponseUtility().buildSuccessResponse(emp.get());
				}
				return new ResponseUtility().dataNotFound(null);
			} else {
				employeeData = employeeService.getEmployeeByQuery(email, Integer.valueOf(employeeID));
				if (!employeeData.get().isEmpty()) {
					return new ResponseUtility().buildSuccessResponse(employeeData.get());
				}
				return new ResponseUtility().dataNotFound(null);
			}

		} catch (BusinessException be) {
			log.error(be.getMessage());
			return new ResponseUtility().buildException(null, be);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ResponseUtility().buildException(null, ex);
		}
	}

}
