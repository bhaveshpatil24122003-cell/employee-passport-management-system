package com.bhavesh.employeepassport.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavesh.employeepassport.dto.EmployeeDTO;
import com.bhavesh.employeepassport.dto.LoginRequest;
import com.bhavesh.employeepassport.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final EmployeeService employeeService;
	private final SecurityContextRepository securityContextRepository =
			new HttpSessionSecurityContextRepository();

	public AuthController(
	        AuthenticationManager authenticationManager,
	        EmployeeService employeeService) {
	    this.authenticationManager = authenticationManager;
	    this.employeeService = employeeService;                                                                                                 
	}
	
	
	@PostMapping("/login")
	public String login(
			@RequestBody LoginRequest loginRequest,
			HttpServletRequest request,
			HttpServletResponse response) {	
		Authentication authentication =
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()));
		SecurityContext securityContext =
				SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);
		securityContextRepository.saveContext(
				securityContext, 
				request, 
				response);
		return "Login successful";
	}
	
}
