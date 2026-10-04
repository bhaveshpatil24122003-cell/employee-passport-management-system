package com.bhavesh.employeepassport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bhavesh.employeepassport.entity.Employee;
import com.bhavesh.employeepassport.repository.EmployeeRepository;

@Configuration
public class AdminConfig {

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Bean
    CommandLineRunner createAdmin(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (employeeRepository.findByEmail(adminEmail).isEmpty()) {
                Employee admin = new Employee();
                admin.setName("Bhavesh Patil");
                admin.setEmail(adminEmail);
                admin.setContactNo("9162457115");
                admin.setDepartment("Administration");
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRole("ADMIN");
                employeeRepository.save(admin);
                System.out.println("ADMIN created successfully.");
            }
        };
    }
}