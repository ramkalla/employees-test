package com.employees.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employees.test.entities.BatchErrors;

public interface BatchErrorLogRepository extends JpaRepository<BatchErrors, Integer> {
}