package com.employees.test.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "batch_process", schema = "dbs_sys")
public class BatchProcess {
	@Id
	private Long id;
	private String processName;
	private Timestamp startTimestamp;
	private Timestamp endTimestamp;
	private String processedFileName;
	private int insertedRecordCount;
	private int updatedRecordCount;
	private int erroredRecordCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Timestamp getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Timestamp startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Timestamp getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Timestamp endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getProcessedFileName() {
		return processedFileName;
	}

	public void setProcessedFileName(String processedFileName) {
		this.processedFileName = processedFileName;
	}

	public int getInsertedRecordCount() {
		return insertedRecordCount;
	}

	public void setInsertedRecordCount(int insertedRecordCount) {
		this.insertedRecordCount = insertedRecordCount;
	}

	public int getUpdatedRecordCount() {
		return updatedRecordCount;
	}

	public void setUpdatedRecordCount(int updatedRecordCount) {
		this.updatedRecordCount = updatedRecordCount;
	}

	public int getErroredRecordCount() {
		return erroredRecordCount;
	}

	public void setErroredRecordCount(int erroredRecordCount) {
		this.erroredRecordCount = erroredRecordCount;
	}
}