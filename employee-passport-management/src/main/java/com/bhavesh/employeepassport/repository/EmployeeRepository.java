package com.bhavesh.employeepassport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavesh.employeepassport.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Optional<Employee> findByEmail(String email);

}

