package com.employees.test.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employees.test.entities.Employee;
import com.employees.test.repo.EmployeesRepo;

@Service("employeeService")
public class EmployeeService {

	private static Logger log = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	public EmployeesRepo employeesRepo;

	public Optional<Employee> getEmployeeByID(Integer id) {
		return employeesRepo.findById(id);

	}

	public Optional<List<Employee>> getEmployeeByQuery(String email, Integer empId) {
		return employeesRepo.findEmployeeByEmailAndId(email, empId);
	}

}