# SpringBootCrudApp

A simple Spring Boot CRUD application for managing departments. This project demonstrates basic CRUD (Create, Read, Update, Delete) operations using Spring Boot, Spring Data JPA, Thymeleaf, and PostgreSQL.

## Features

- **Department Management**: List, add, edit, and delete departments.
- **Form Validation**: Server-side validation for department details.
- **Responsive UI**: Built with Bootstrap for a modern and responsive user experience.
- **Database Integration**: Persists data in a PostgreSQL database.

## Technologies Used

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Data JPA**: For database interaction.
- **Thymeleaf**: Server-side Java template engine.
- **PostgreSQL**: Relational database.
- **Lombok**: To reduce boilerplate code.
- **Bootstrap 5**: For front-end styling.
- **JUnit 5 & H2**: For unit and integration testing.

## Prerequisites

Before running the application, ensure you have the following installed:

- JDK 17 or higher
- Maven 3.6+
- PostgreSQL server

## Database Setup

1. Open your PostgreSQL terminal or any database client (like pgAdmin).
2. Create a database named `appdb`:
   ```sql
   CREATE DATABASE appdb;
   ```
3. Update the `src/main/resources/application.properties` file with your PostgreSQL credentials:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## Getting Started

### Clone the repository

```bash
git clone https://github.com/chintanpatel/SpringBootCrudApp.git
cd SpringBootCrudApp
```

### Build the application

```bash
./mvnw clean install
```

### Run the application

```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.

## Project Structure

- `src/main/java`: Contains the Java source code.
  - `org.chintanpatel.springbootcrudapp.department`: Contains Department-related entities, controllers, services, and repositories.
- `src/main/resources`:
  - `templates/`: Thymeleaf HTML templates.
  - `static/`: Static resources like CSS and JS (Bootstrap).
  - `application.properties`: Main application configuration.
- `src/test/java`: Contains unit and integration tests.

## Running Tests

To run the automated tests, use the following command:

```bash
./mvnw test
```

## Author

- **Chintan Patel** – [GitHub](https://github.com/chintanpatel)
