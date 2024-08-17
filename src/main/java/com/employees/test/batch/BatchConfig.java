package com.employees.test.batch;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.employees.test.entities.BatchErrors;
import com.employees.test.entities.Employee;
import com.employees.test.entities.EmployeePhone;
import com.employees.test.model.EmployeeBatchData;
import com.employees.test.repo.BatchErrorLogRepository;
import com.employees.test.repo.BatchProcessRepository;
import com.employees.test.repo.EmployeesRepo;

import io.micrometer.core.instrument.util.StringUtils;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private EmployeesRepo employeeRepository;

	@Autowired
	private BatchProcessRepository batchProcessRepository;

	@Autowired
	private BatchErrorLogRepository batchErrorLogRepository;

	@Autowired
	private BatchJobCompletionListener jobCompletionListener;

	@Value("${inputfilepath}")
	private String inputFilePath;

	@Bean
	public Job employeeJob() {
		return new JobBuilder("employeeJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(jobCompletionListener)
				.start(employeeStep()).build();
	}
	@Bean
	public Step employeeStep() {
		return new StepBuilder("employeeStep", jobRepository).<EmployeeBatchData, Employee>chunk(100, transactionManager)
				.reader(csvReader()).processor(processor()).writer(writer()).build();
	}

	@Bean
	public FlatFileItemReader<EmployeeBatchData> csvReader() {
		return new FlatFileItemReaderBuilder<EmployeeBatchData>().name("employeeItemReader")
				.resource(new FileSystemResource(inputFilePath)).delimited()
				.names(new String[] { "employeeId", "firstName", "lastName", "email", "phoneNumber", "doj", "salary" })
				.fieldSetMapper(new EmployeeFieldSetMapper()).build();
	}

	@Bean
	public ItemProcessor<EmployeeBatchData, Employee> processor() {
		return employeeDTO -> {
			Optional<Employee> existingEmployee = employeeRepository.findById(employeeDTO.getEmployeeId());
			Employee employee = existingEmployee.orElse(new Employee());
			employee.setFirstName(employeeDTO.getFirstName());
			employee.setLastName(employeeDTO.getLastName());
			employee.setEmail(employeeDTO.getEmail());
			employee.setDoj(employeeDTO.getDoj());
			employee.setSalary(employeeDTO.getSalary());
			if(StringUtils.isEmpty(employee.getCreatedBy())){
				employee.setCreateDate(new Date());
				employee.setCreatedBy("admin");
			}else {
				employee.setUpdatedDate(new Date());
				employee.setUpdatedBy("admin");
			}
			List<EmployeePhone> phoneNumbers = new LinkedList<>();
			for (String phone : employeeDTO.getPhoneNumber()) {
				EmployeePhone empPhone = new EmployeePhone();
				empPhone.setPhoneNumber(phone);
				empPhone.setEmployee(employee);
				empPhone.setCreateDate(new Date());
				empPhone.setCreatedBy("admin");
				phoneNumbers.add(empPhone);
			}
			employee.setEmployeePhones(phoneNumbers);
			return employee;
		};
	}

	@Bean
	public ItemWriter<Employee> writer() {
		return employees -> {
			for (Employee employee : employees) {
				try {
					employeeRepository.save(employee);
				} catch (Exception e) {
					BatchErrors batchErrorLog = new BatchErrors();
					batchErrorLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
					batchErrorLog.setFilename(inputFilePath);
					batchErrorLog.setMessage("Error processing Employee ID: " + employee.getEmployeeid());
					batchErrorLog.setActualError(e.getMessage());
					batchErrorLogRepository.save(batchErrorLog);
				}
			}
		};
	}
}
