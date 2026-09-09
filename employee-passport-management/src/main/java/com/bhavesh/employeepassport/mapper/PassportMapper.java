package com.bhavesh.employeepassport.mapper;

import com.bhavesh.employeepassport.dto.PassportDTO;
import com.bhavesh.employeepassport.entity.Employee;
import com.bhavesh.employeepassport.entity.Passport;

public class PassportMapper {
	
	public static Passport toEntity( PassportDTO passportDTO, Employee employee) {
		
		Passport passport = new Passport();
		
		passport.setPassportId(passportDTO.getPassportId());
		passport.setPassportNo(passportDTO.getPassportNo());
		passport.setIssueDate(passportDTO.getIssueDate());
		passport.setExpiryDate(passportDTO.getExpiryDate());
		passport.setNationality(passportDTO.getNationality());
		passport.setEmployee(employee);
		return passport;
	}
	
	public static PassportDTO toDTO(Passport passport) {

	    PassportDTO passportDTO = new PassportDTO();

	    passportDTO.setPassportId(passport.getPassportId());
	    passportDTO.setPassportNo(passport.getPassportNo());
	    passportDTO.setIssueDate(passport.getIssueDate());
	    passportDTO.setExpiryDate(passport.getExpiryDate());
	    passportDTO.setNationality(passport.getNationality());

	    passportDTO.setEmployeeId(passport.getEmployee().getEmployeeId());

	    return passportDTO;
	}

}
