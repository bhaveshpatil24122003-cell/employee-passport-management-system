package com.bhavesh.employeepassport.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhavesh.employeepassport.entity.Employee;
import com.bhavesh.employeepassport.repository.EmployeeRepository;


@Service
public class EmployeeUserDetailsService implements UserDetailsService {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeUserDetailsService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email)
	      throws UsernameNotFoundException {	 
		 Employee employee = employeeRepository
	                .findByEmail(email)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("Employee not found"));
		return User.builder()
				.username(employee.getEmail())
				.password(employee.getPassword())
				.roles(employee.getRole())
				.build();
				
	}

}
