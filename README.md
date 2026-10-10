
# Employee Passport Management System

A Spring Boot application for managing employee details and passport records using REST APIs.

## Technologies Used
- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- Spring Security
- Session-Based Authentication
- MySQL
- Hibernate
- Maven
- Lombok
- HTML, CSS, JavaScript

## Features
- Employee Management (CRUD)
- Passport Management (CRUD)
- Employee-Passport One-to-One Relationship
- Passport Expiry Tracking
- Admin and Employee Roles
- Secure Login
- Employee Self-Service
- DTO and Mapper Architecture

## API Endpoints

### Authentication
- POST /auth/login

### Employee Management
- POST /employees
- GET /employees
- GET /employees/{employeeId}
- PUT /employees/{employeeId}
- DELETE /employees/{employeeId}
- GET /employees/me
- PUT /employees/me

### Passport Management
- POST /passports
- GET /passports
- GET /passports/{passportId}
- PUT /passports/{passportId}
- DELETE /passports/{passportId}
- GET /passports/me
- PUT /passports/me
- GET /passports/expiring

## Database Setup

```sql
CREATE DATABASE employee_passport_db;
```

## Environment Variables

```env
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
ADMIN_EMAIL=your_admin_email
ADMIN_PASSWORD=your_admin_password
```

Do not upload real credentials to GitHub.

## Project Architecture

Controller
    |
    v
Service
    |
    v
Repository
    |
    v
MySQL Database

DTO and Mapper classes are used to transfer and convert data between layers.

## Run Application

```bash
./mvnw spring-boot:run
```

For Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Run the command inside the folder containing pom.xml.

## API Base URL

http://localhost:8080

## Authentication

The application uses Spring Security with session-based authentication.

Admin and Employee roles have different access permissions.

## Tools
- Spring Tool Suite / Eclipse
- VS Code
- MySQL Workbench
- Postman
- Git & GitHub

## Author
Bhavesh Patil

