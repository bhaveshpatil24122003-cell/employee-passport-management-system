package com.bhavesh.employeepassport.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavesh.employeepassport.entity.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {
	 Optional<Passport> findByEmployee_Email(String email);
	 
	 List<Passport> findByExpiryDateBetween(
			 LocalDate startDate,
			 LocalDate endDate);
	 boolean existsByEmployee_EmployeeId(Long employeeId);
}
