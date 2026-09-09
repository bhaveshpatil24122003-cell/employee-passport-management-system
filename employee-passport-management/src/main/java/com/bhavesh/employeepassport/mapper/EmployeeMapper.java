package com.bhavesh.employeepassport.mapper;

import com.bhavesh.employeepassport.dto.EmployeeDTO;

import com.bhavesh.employeepassport.entity.Employee;

public class EmployeeMapper {
	
	public static Employee toEntity(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setContactNo(employeeDTO.getContactNo());
		employee.setDepartment(employeeDTO.getDepartment());
		employee.setPassword(employeeDTO.getPassword());
		employee.setRole(employeeDTO.getRole());
		
		return employee;	
	}
	
	public static EmployeeDTO toDTO(Employee employee) {
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setContactNo(employee.getContactNo());
		employeeDTO.setDepartment(employee.getDepartment());
		employeeDTO.setRole(employee.getRole());
		
		return employeeDTO;
	}
}
