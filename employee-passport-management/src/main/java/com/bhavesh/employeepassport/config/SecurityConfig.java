package com.bhavesh.employeepassport.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bhavesh.employeepassport.security.EmployeeUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(
				        "/auth/login",
				        "/HTML/login.html",
				        "/CSS/**",
				        "/JAVASCRIPT/**")
				.permitAll()

				.requestMatchers(
				        "/HTML/admin-dashboard.html",
				        "/HTML/employees.html",
				        "/HTML/passports.html",
				        "/HTML/expiring.html")
				.hasRole("ADMIN")

				.requestMatchers(
				        "/HTML/employee-dashboard.html",
				        "/HTML/my-profile.html",
				        "/HTML/my-passport.html")
				.hasAnyRole("ADMIN", "EMPLOYEE")
		        
				.requestMatchers(
						HttpMethod.GET, 
						"/employees/me")
				.hasAnyRole("ADMIN", "EMPLOYEE")
				
				.requestMatchers(
						HttpMethod.PUT,
						"/employees/me")
				.hasAnyRole("ADMIN", "EMPLOYEE")
				
				.requestMatchers(HttpMethod.POST, 
						"/employees")
		        .hasRole("ADMIN")
		        
				.requestMatchers(
						HttpMethod.GET,
						"/employees/**")
				.hasRole("ADMIN")
				
				.requestMatchers(
						HttpMethod.PUT,
						"/employees/**")
				.hasRole("ADMIN")
				
				.requestMatchers(
						HttpMethod.DELETE,
						"/employees/**")
				.hasRole("ADMIN")
				
				.requestMatchers(HttpMethod.GET,
						"/passports/me")
				.hasAnyRole("ADMIN", "EMPLOYEE")
				
				.requestMatchers(HttpMethod.PUT, 
						"/passports/me")
				.hasAnyRole("ADMIN", "EMPLOYEE")

				.requestMatchers(HttpMethod.GET,
						"/passports/**")
				.hasRole("ADMIN")

				.requestMatchers(HttpMethod.POST,
						"/passports/**")
				.hasRole("ADMIN")

				.requestMatchers(HttpMethod.PUT,
						"/passports/**")
				.hasRole("ADMIN")

				.requestMatchers(HttpMethod.DELETE,
						"/passports/**")
				.hasRole("ADMIN")
				
				.anyRequest().authenticated());
		  http.logout(logout -> logout
		            .logoutUrl("/logout")
		            .logoutSuccessUrl("/HTML/login.html")
		            .invalidateHttpSession(true)
		            .deleteCookies("JSESSIONID")
		            .permitAll());
	    return http.build();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(
			  EmployeeUserDetailsService employeeUserDetailsService) {
		
		  DaoAuthenticationProvider provider = 
				  new   DaoAuthenticationProvider(employeeUserDetailsService);
		  
		  provider.setPasswordEncoder(passwordEncoder());
		  return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}

}
