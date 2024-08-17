package com.employees.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employees.test.entities.BatchProcess;

public interface BatchProcessRepository extends JpaRepository<BatchProcess, Long> {
}
