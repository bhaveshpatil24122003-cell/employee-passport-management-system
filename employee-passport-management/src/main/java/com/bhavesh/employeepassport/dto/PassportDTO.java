package com.bhavesh.employeepassport.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportDTO {
	
	private Long passportId;
	
	@NotBlank(message = "Passport number is required")
	private String passportNo;
	
	@NotNull(message = "Issue date is required")
	private LocalDate issueDate;
	
	@NotNull(message = "Expiry date is required")
	@Future(message = "Expiry date must be in the future")
	private LocalDate expiryDate;
	
	@NotBlank(message = "Nationality is required")
	private String nationality;
	
	@NotNull(message = "Employee ID is required")
	private Long employeeId;

}
