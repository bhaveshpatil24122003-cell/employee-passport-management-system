package com.bhavesh.employeepassport.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long passportId;
	private String passportNo;
	private LocalDate issueDate;
	private LocalDate expiryDate;
	private String nationality;
	
	@OneToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
     
}
