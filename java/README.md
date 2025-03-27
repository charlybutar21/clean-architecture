# Product Management 

## Overview
Product Management is a Spring Boot-based application designed to manage product-related data efficiently. Built with Java 17 and Spring Boot 3.4.3, this application leverages various modern technologies to ensure scalability, maintainability, and ease of deployment.

## Features
- Product CRUD Operations: Create and List product data.
- Kafka Integration: Asynchronous messaging and event-driven architecture.
- MySQL Database: Relational database for product data storage.
- FlywayDB: Database migration handling.
- JPA (Java Persistence API): ORM for database interaction.
- ModelMapper: Simplifies object mapping.
- Lombok: Reduces boilerplate code.
- Spring MVC: Handles HTTP requests and responses.
- Docker: Containerization for easy deployment.

## Technologies Used
- Java 17
- Spring Boot 3.4.3
- Spring MVC (REST-ful API development)
- Spring Data JPA (Persistence layer)
- FlywayDB (Database version control)
- Kafka (Event-driven messaging system)
- Lombok (Boilerplate code reduction)
- ModelMapper (Object mapping)
- Spring Boot Validation (Input validation)
- Docker (Containerization)


### Installation & Running the Application
1. Clone the repository:
```bash
git clone https://github.com/charlybutar21/clean-architecture
cd clean-architecture
```

2. Start services using Docker:
```bash
docker-compose up -d
```

3. Run the Spring Boot application:
```bash
mvn clean spring-boot:run
```

4. Database Migration (Flyway)
Flyway automatically applies migrations at startup. To apply manually:
```bash
mvn flyway:migrate 
```
