---

## 📸 Application Screenshots

### 🔐 Login Page
Secure login page for Admin and Employee authentication.

<img width="1895" height="853" alt="Screenshot 2026-09-09 163703" src="https://github.com/user-attachments/assets/d806afd3-beb4-45cb-bb09-fe218776989a" />

### 🧑‍💼 Admin Dashboard
Admin dashboard for managing employees, passports, and monitoring passport expiry.

<img width="1917" height="862" alt="Screenshot 2026-09-09 163742" src="https://github.com/user-attachments/assets/4058f0ac-a78a-4f2e-8888-6385d66cc630" />

### 👤 Employee Dashboard
Employee dashboard providing access to personal profile and passport information.

<img width="1916" height="852" alt="Screenshot 2026-09-09 163835" src="https://github.com/user-attachments/assets/133b802d-5b6c-4ba3-b9cb-8e08075018e2" />

---

# Employee Passport Management System

A full-stack **Employee Passport Management System** developed using **Java, Spring Boot, Spring Security, Spring Data JPA, MySQL, HTML, CSS, and JavaScript**.

The application provides a centralized platform for managing employee records and passport information. It supports role-based access for **Admin** and **Employee**, along with passport expiry tracking and secure authentication.

---

## 🚀 Key Features

### 👨‍💼 Admin Module
- Secure Admin Login
- Add New Employees
- View All Employees
- Update Employee Details
- Delete Employee Records
- Add Passport Details
- Update Passport Information
- View Employee Passports
- Track Expiring Passports
- Admin Dashboard

### 👤 Employee Module
- Secure Employee Login
- Employee Dashboard
- View Own Profile
- Update Own Profile
- View Own Passport Details

### 🛂 Passport Management
- Add Passport Details
- Update Passport Details
- View Passport Information
- Track Passport Expiry Date
- One-to-One Employee and Passport Relationship

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Backend Programming |
| Spring Boot | Application Development |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database Operations |
| Hibernate | ORM |
| MySQL | Database |
| REST API | Client-Server Communication |
| HTML5 | Frontend Structure |
| CSS3 | UI Styling |
| JavaScript | Frontend Functionality |
| Maven | Dependency Management |
| Git & GitHub | Version Control |

---

## 🏗️ Project Architecture

The application follows a layered architecture:

    Frontend (HTML / CSS / JavaScript)
                    ↓
               Controller
                    ↓
                 Service
                    ↓
              Repository
                    ↓
              MySQL Database

Data conversion is handled using:

    DTO ↔ Mapper ↔ Entity

This structure separates presentation, business logic, and database operations, making the application easier to maintain and extend.

---

## 📂 Main Modules

- Authentication & Authorization
- Employee Management
- Passport Management
- Admin Dashboard
- Employee Dashboard
- Passport Expiry Tracking
- Exception Handling

---

## 🔗 Entity Relationship

The system maintains a **One-to-One relationship** between Employee and Passport.

    Employee  1  ─────────  1  Passport

Each employee can have one associated passport record.

---

## 🔐 Security

The project uses **Spring Security** for authentication and authorization.

Two application roles are supported:

- **ADMIN** – Full access to employee and passport management.
- **EMPLOYEE** – Limited access to personal profile and passport information.

---

## 🗄️ Database Configuration

Create the MySQL database:

```sql
CREATE DATABASE employee_passport_db;
