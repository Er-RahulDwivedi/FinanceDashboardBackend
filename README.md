# Finance Dashboard Backend API

A robust, secure Spring Boot REST API for managing financial records, user roles, and dashboard analytics. This project was built to demonstrate clean architecture, separation of concerns, and Role-Based Access Control (RBAC) for a backend engineering assessment.

##  Tech Stack

* **Language:** Java 21+
* **Framework:** Spring Boot 3.x
* **Security:** Spring Security (Basic Authentication)
* **Database:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **Validation:** Jakarta Bean Validation

##  Architecture & Design Decisions

To ensure clean, maintainable, and readable code, the following design patterns and decisions were implemented:

1. **N-Tier Architecture:** Strict separation between Controllers, Services, and Repositories.
2. **No Lombok (Explicit Code):** To prioritize absolute transparency, all constructors, getters, and setters are explicitly defined.
3. **DTO Pattern:** Data Transfer Objects are used to separate API payloads from Database Entities for strict input validation.
4. **Database-Level Aggregation:** Dashboard summaries are calculated using JPQL queries directly in the database layer for optimal performance.
5. **Centralized Exception Handling:** A `@RestControllerAdvice` class intercepts exceptions and formats them into clean HTTP responses.
6. **Stateless Security:** Implemented Spring Security with Basic Authentication for rapid, secure API testing.

##  Role-Based Access Control (RBAC)

* **Admin (`ROLE_ADMIN`):** Full access. Can create/manage users, and perform all CRUD operations.
* **Analyst (`ROLE_ANALYST`):** Read-only access to individual records and dashboard summaries.
* **Viewer (`ROLE_VIEWER`):** Can only view aggregated dashboard summaries.

##  Setup and Installation

### 1. Database Configuration
By default, the application expects a local MySQL instance. Update the `src/main/resources/application.yml` with your MySQL credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/finance_db?createDatabaseIfNotExist=true
    username: root
    password: your_password


2. Running the Application
Run the application using your IDE or via the command line:

Bash
mvn spring-boot:run
The server will start on http://localhost:8080.

3. Data Seeding (First Login)
To solve the "chicken and egg" problem of requiring an admin to create users, the application uses a CommandLineRunner to automatically seed a default Admin user on startup if one does not exist.

Username: admin

Password: admin123

📡 Core API Endpoints
All endpoints require Basic Authentication.

User Management (Admins Only)
GET /api/users - List all users.

POST /api/users - Create a new user (assigns Viewer, Analyst, or Admin role).

Financial Records
GET /api/records - View all records (Admin, Analyst).

POST /api/records - Create a new record (Admin).

PUT /api/records/{id} - Update a record (Admin).

DELETE /api/records/{id} - Delete a record (Admin).

Dashboard Analytics
GET /api/dashboard/summary - Returns aggregated totals, net balance, category breakdowns, and recent activity (Admin, Analyst, Viewer).

 How to Test
Open an API client like Postman.

Set the Authorization tab to Basic Auth.

Enter the default credentials (admin / admin123).

Create new users with different roles via POST /api/users.

Create a few INCOME and EXPENSE records via POST /api/records.

Hit the GET /api/dashboard/summary endpoint to see the database aggregations at work.

Change your Basic Auth credentials to a "Viewer" user and attempt to create a record to verify the 403 Forbidden security block.
