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

import com.bhavesh.employeepassport.dto.PassportDTO;
import com.bhavesh.employeepassport.service.PassportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/passports")
public class PassportController {

    private final PassportService passportService;

    public PassportController(
    		PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping
    public PassportDTO addPassport(
    		@Valid @RequestBody PassportDTO passportDTO) {
        return passportService.addPassport(passportDTO);
    }

    @GetMapping
    public List<PassportDTO> getAllPassports() {
        return passportService.getAllPassports();
    }

    @GetMapping("/me")
    public PassportDTO getMyPassport(
    		Authentication authentication) {
        String email = authentication.getName();
        return passportService.getPassportByEmployeeEmail(email);
    }
    
    @PutMapping("/me")
    public PassportDTO updateMyPassport(
            Authentication authentication,
            @RequestBody PassportDTO passportDTO) {
        String email = authentication.getName();
        return passportService.updateMyPassport(email, passportDTO);
    }
    
    @GetMapping("/expiring")
    public List<PassportDTO> getExpiringPassports() {
        return passportService.getExpiringPassports();
    }
    
    @GetMapping("/{passportId}")
    public PassportDTO getPassportById(
    		@PathVariable Long passportId) {
        return passportService.getPassportById(passportId);
    }

    @PutMapping("/{passportId}")
    public PassportDTO updatePassport(
    		@PathVariable Long passportId, 
    		@RequestBody PassportDTO passportDTO) {
        return passportService.updatePassport(passportId, passportDTO);
    }

    @DeleteMapping("/{passportId}")
    public void deletePassport(
    		@PathVariable Long passportId) {
        passportService.deletePassport(passportId);
    }
    
  
}