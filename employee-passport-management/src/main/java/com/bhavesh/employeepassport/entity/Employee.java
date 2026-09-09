package com.bhavesh.employeepassport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long employeeId;
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	private String contactNo;
	private String department;
	
	private String password;
	private String role;

}
