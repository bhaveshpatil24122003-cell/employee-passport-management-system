package com.bhavesh.employeepassport.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	
	private Long employeeId;
	
    @NotBlank(message = "Name is required")
	private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email")
	private String email;
    
    @NotBlank(message = "Contact number is required")
	private String contactNo;
    
    @NotBlank(message = "Department is required")
	private String department;
	
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
    
	private String role;

}
