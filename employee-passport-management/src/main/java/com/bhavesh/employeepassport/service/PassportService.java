package com.bhavesh.employeepassport.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bhavesh.employeepassport.dto.PassportDTO;
import com.bhavesh.employeepassport.entity.Employee;
import com.bhavesh.employeepassport.entity.Passport;
import com.bhavesh.employeepassport.exception.DuplicateResourceException;
import com.bhavesh.employeepassport.exception.ResourceNotFoundException;
import com.bhavesh.employeepassport.mapper.PassportMapper;
import com.bhavesh.employeepassport.repository.EmployeeRepository;
import com.bhavesh.employeepassport.repository.PassportRepository;

@Service
public class PassportService {

    private final PassportRepository passportRepository;
    private final EmployeeRepository employeeRepository;

    public PassportService(
            PassportRepository passportRepository,
            EmployeeRepository employeeRepository) {
        this.passportRepository = passportRepository;
        this.employeeRepository = employeeRepository;
    }

    
    public PassportDTO addPassport(PassportDTO passportDTO) {
    	Employee employee = employeeRepository
    	        .findById(passportDTO.getEmployeeId())
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Employee not found"));
    	if (passportRepository.existsByEmployee_EmployeeId(
    	        passportDTO.getEmployeeId())) {
    	    throw new DuplicateResourceException(
    	            "Passport already exists for this employee");
    	}
        Passport passport = PassportMapper.toEntity(passportDTO, employee);
        Passport savedPassport = passportRepository.save(passport);
        return PassportMapper.toDTO(savedPassport);
    }

    
    public List<PassportDTO> getAllPassports() {
        List<Passport> passports = passportRepository.findAll();
        return passports.stream()
                .map(PassportMapper::toDTO)
                .toList();
    }

    
    public PassportDTO getPassportById(Long passportId) {
        Passport passport = passportRepository
                .findById(passportId)
                .orElseThrow(() ->
                         new ResourceNotFoundException("Passport not found"));
        return PassportMapper.toDTO(passport);
    }

    
    public PassportDTO updatePassport(
            Long passportId,
            PassportDTO passportDTO) {
    	Passport passport = passportRepository
    	        .findById(passportId)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Passport not found"));
    	Employee employee = employeeRepository
    	        .findById(passportDTO.getEmployeeId())
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Employee not found"));
        passport.setPassportNo(passportDTO.getPassportNo());
        passport.setIssueDate(passportDTO.getIssueDate());
        passport.setExpiryDate(passportDTO.getExpiryDate());
        passport.setNationality(passportDTO.getNationality());
        passport.setEmployee(employee);
        Passport updatedPassport = passportRepository.save(passport);
        return PassportMapper.toDTO(updatedPassport);
    }

    
    public void deletePassport(Long passportId) {
        if (!passportRepository.existsById(passportId)) {
        	throw new ResourceNotFoundException("Passport not found");
        }
        passportRepository.deleteById(passportId);
    }
    
    
    public PassportDTO getPassportByEmployeeEmail(String email) {

        Passport passport = passportRepository
                .findByEmployee_Email(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Passport not found"));
        return PassportMapper.toDTO(passport);
    }
    
    
    public PassportDTO updateMyPassport(
            String email,
            PassportDTO passportDTO) {
    	Passport passport = passportRepository
    	        .findByEmployee_Email(email)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Passport not found"));
        passport.setPassportNo(passportDTO.getPassportNo());
        passport.setIssueDate(passportDTO.getIssueDate());
        passport.setExpiryDate(passportDTO.getExpiryDate());
        passport.setNationality(passportDTO.getNationality());
        Passport updatedPassport = passportRepository.save(passport);
        return PassportMapper.toDTO(updatedPassport);
    }
    
    
    public List<PassportDTO> getExpiringPassports() {
        LocalDate today = LocalDate.now();
        LocalDate next30Days = today.plusDays(30);
        List<Passport> passports =
                passportRepository.findByExpiryDateBetween(today, next30Days);
        return passports.stream()
                .map(PassportMapper::toDTO)
                .toList();
    }
}