# Clean Architecture

## Overview
This project follows the Clean Architecture principles, ensuring a clear separation of concerns and maintainability. It is built using Java 17 and Spring Boot 3.4.3, and integrates Kafka, MySQL, FlywayDB, JPA, ModelMapper, Lombok, Spring MVC, and Docker.
![img.png](assets/images/ca-flow.png)

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

## Clean Architecture Layers
Clean Architecture organizes the project into four main layers, ensuring separation of concerns and flexibility:
![img.png](assets/images/ca-layer.png)
### 1. Enterprise Business Rules (Entities)
- This layer contains core business logic and domain entities.
- It is independent of any frameworks, databases, or external dependencies.

### 2. Application Business Rules (Use Cases)
- Contains application-specific business logic (e.g., services and use cases).
- Implements use case logic without being dependent on external frameworks.

### 3. Interface Adapters (Gateways, Controllers, and Presenters)
- Translates data between the application and external sources (APIs, databases, etc.).
- Contains Controllers (handling HTTP requests) and Gateways (handling data persistence).

### 4. Frameworks & Drivers (Infrastructure Layer)
- Implements framework-specific details such as database connections, messaging queues, and web frameworks.
- Includes Spring Boot, JPA, Kafka, Flyway, and Docker.

## Getting Started
### Prerequisites
- Install Java 17, Docker, and Docker Compose
- Install MySQL 8.0 (or use Docker)

### Running the Application
1. Clone the repository:
```bash
git clone https://github.com/your-repo.git
cd your-repo
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

## Conclusion
This project applies Clean Architecture principles for a modular, scalable, and maintainable application. It ensures flexibility and separation of concerns, making it easy to test, extend, and integrate with new technologies.

