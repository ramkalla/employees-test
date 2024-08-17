package com.employees.test.batch;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.employees.test.entities.BatchProcess;
import com.employees.test.repo.BatchProcessRepository;

@Component
public class BatchJobCompletionListener implements JobExecutionListener {

	@Autowired
	private BatchProcessRepository batchProcessRepository;

	@Value("${inputfilepath}")
	private String inputFilePath;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		BatchProcess batchProcess = new BatchProcess();
		batchProcess.setId(jobExecution.getJobId());
		batchProcess.setProcessName("Employee Upsert Batch Process");
		batchProcess.setStartTimestamp(new Timestamp(System.currentTimeMillis()));
		batchProcess.setProcessedFileName(inputFilePath);
		batchProcessRepository.save(batchProcess);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		Optional<BatchProcess> optionalBatchProcess = batchProcessRepository.findById(jobExecution.getJobId());
		if (optionalBatchProcess.isPresent()) {
			BatchProcess batchProcess = optionalBatchProcess.get();
			batchProcess.setEndTimestamp(new Timestamp(System.currentTimeMillis()));
			long insertedCount = jobExecution.getStepExecutions().stream().mapToLong(StepExecution::getWriteCount)
					.sum();
			int errorCount = jobExecution.getAllFailureExceptions().size();
			// Update batch process record
			batchProcess.setInsertedRecordCount((int) insertedCount);
			batchProcess.setErroredRecordCount(errorCount);
			batchProcess.setProcessedFileName(renameProcessedFile());
			batchProcessRepository.save(batchProcess);
		}
	}

	private String renameProcessedFile() {
		File oldFile = new File(inputFilePath);

		if (!oldFile.exists()) {
			throw new IllegalStateException("File not found: " + inputFilePath);
		}
		if (!oldFile.canRead()) {
			throw new IllegalStateException("Cannot read file: " + oldFile.getAbsolutePath());
		}
		if (!oldFile.canWrite()) {
			throw new IllegalStateException("Cannot write to file: " + oldFile.getAbsolutePath());
		}

		// Check if the directory has write permissions
		File parentDir = oldFile.getParentFile();
		if (!parentDir.canWrite()) {
			throw new IllegalStateException("Cannot write to directory: " + parentDir.getAbsolutePath());
		}

		// Construct the new file name with absolute paths
		String newFileName = "processed-employees-" + new SimpleDateFormat("MM-dd-yy-HH-mm-ss").format(new Date())
				+ ".csv";
		File newFile = new File(parentDir, newFileName);
		// Attempt to rename the file
		if (oldFile.renameTo(newFile)) {
			return newFile.getAbsolutePath(); // Return the absolute path of the renamed file
		} else {
			throw new IllegalStateException("Failed to rename file: " + oldFile.getAbsolutePath());
		}
	}
}