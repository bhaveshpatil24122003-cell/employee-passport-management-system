package com.bhavesh.employeepassport.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavesh.employeepassport.dto.EmployeeDTO;
import com.bhavesh.employeepassport.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	  public EmployeeController(
			  EmployeeService employeeService) {
		  this.employeeService = employeeService;
	}
	  
	  
	  @PostMapping
	  public EmployeeDTO addEmployee(
			  @Valid @RequestBody EmployeeDTO employeeDTO) {
		  return employeeService.addEmployee(employeeDTO);
	  }
	  
	  @GetMapping
	  public List<EmployeeDTO> getAllEmployees(){
		  return employeeService.getAllEmployees();
	  }
	  
	  @GetMapping("/me")
	  public EmployeeDTO getMyProfile(
			  Authentication authentication) {
	      String email = authentication.getName();
	      return employeeService.getEmployeeByEmail(email);
	  }
	  
	  @GetMapping("/{employeeId}")
	  public EmployeeDTO getEmployeeById(
			  @PathVariable Long employeeId) {
		  return employeeService.getEmployeeById(employeeId);
	  }
	  
	  @PutMapping("/me")
	  public EmployeeDTO updateMyProfile(
	          Authentication authentication,
	          @RequestBody EmployeeDTO employeeDTO) {
	      String email = authentication.getName();
	      return employeeService.updateEmployeeByEmail(email, employeeDTO);
	  }
	  
	  @PutMapping("/{employeeId}")
	  public EmployeeDTO updateEmployee(
			  @PathVariable Long employeeId,
			  @RequestBody EmployeeDTO employeeDTO) {
		  return employeeService.updateEmployee(employeeId, employeeDTO);
	  }
	  
	  @DeleteMapping("/{employeeId}")
	  public void deleteEmployee(
			  @PathVariable Long employeeId) {
		   employeeService.deleteEmployee(employeeId);
	  }
}
