package com.bhavesh.employeepassport.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bhavesh.employeepassport.dto.EmployeeDTO;
import com.bhavesh.employeepassport.entity.Employee;
import com.bhavesh.employeepassport.exception.ResourceNotFoundException;
import com.bhavesh.employeepassport.mapper.EmployeeMapper;
import com.bhavesh.employeepassport.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	
		public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
			this.employeeRepository =  employeeRepository;
			this.passwordEncoder = passwordEncoder;
		}
		

        public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        	Employee employee = EmployeeMapper.toEntity(employeeDTO);
        	 employee.setPassword(
        	            passwordEncoder.encode(employeeDTO.getPassword()) );
        	Employee savedEmployee = employeeRepository.save(employee);
			return EmployeeMapper.toDTO(savedEmployee);		  	
        }
      
        
        public List<EmployeeDTO> getAllEmployees(){
        	
        	List<Employee> employees = employeeRepository.findAll();
        	
        	return employees.stream().map(EmployeeMapper::toDTO).toList();  	
        }
        
        
        public EmployeeDTO getEmployeeById(Long employeeId) {

            Employee employee = employeeRepository
                    .findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee not found"));
            return EmployeeMapper.toDTO(employee);
        }
        
        
        public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
            Employee employee = employeeRepository
                    .findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee not found"));
            employee.setName(employeeDTO.getName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setContactNo(employeeDTO.getContactNo());
            employee.setDepartment(employeeDTO.getDepartment());
            Employee updateEmployee = employeeRepository.save(employee);
            return EmployeeMapper.toDTO(updateEmployee);
        }
        
        
        public void deleteEmployee(Long employeeId) {
            if (!employeeRepository.existsById(employeeId)) {
                throw new ResourceNotFoundException("Employee not found");
            }
            employeeRepository.deleteById(employeeId);
        }
        
        
        public EmployeeDTO getEmployeeByEmail(String email) {
            Employee employee = employeeRepository
                    .findByEmail(email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee not found"));
            return EmployeeMapper.toDTO(employee);  
        }
        
        
        public EmployeeDTO updateEmployeeByEmail(
                String email,
                EmployeeDTO employeeDTO) {
            Employee employee = employeeRepository
                    .findByEmail(email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee not found"));
            employee.setName(employeeDTO.getName());
            employee.setContactNo(employeeDTO.getContactNo());
            employee.setDepartment(employeeDTO.getDepartment());
            Employee updateEmployee = employeeRepository.save(employee);
            return EmployeeMapper.toDTO(updateEmployee);     	
        }
}
