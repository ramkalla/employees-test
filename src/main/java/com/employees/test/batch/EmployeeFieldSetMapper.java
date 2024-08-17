package com.employees.test.batch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.employees.test.model.EmployeeBatchData;

public class EmployeeFieldSetMapper implements FieldSetMapper<EmployeeBatchData> {

	@Override
	public EmployeeBatchData mapFieldSet(FieldSet fieldSet) throws BindException {
		EmployeeBatchData employeeDTO = new EmployeeBatchData();
		employeeDTO.setEmployeeId(Integer.valueOf(fieldSet.readString("employeeId")));
		employeeDTO.setFirstName(fieldSet.readString("firstName"));
		employeeDTO.setLastName(fieldSet.readString("lastName"));
		employeeDTO.setEmail(fieldSet.readString("email"));
		employeeDTO.setDoj(fieldSet.readDate("doj", "yyyy-MM-dd"));
		employeeDTO.setSalary(fieldSet.readFloat("salary"));

		String phoneNumbersString = fieldSet.readString("phoneNumber");
		List<String> phoneNumbers = Arrays.asList(phoneNumbersString.split(","));
		employeeDTO.setPhoneNumber(phoneNumbers);

		return employeeDTO;
	}
}
