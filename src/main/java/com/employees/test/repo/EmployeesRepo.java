package com.employees.test.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employees.test.entities.Employee;

@Repository("employeesRepo")
public interface EmployeesRepo extends JpaRepository<Employee, Integer> {

	@Query(" FROM Employee emp WHERE emp.email = :email or  emp.employeeid = :empid")
	Optional<List<Employee>> findEmployeeByEmailAndId(@Param("email") String email, @Param("empid") Integer empid);

}