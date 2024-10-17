# Spring Boot API Consumer Showcase

This Spring Boot web application showcases consuming external APIs using **RestTemplate**. It connects to another service that manages **Employee** and **Department** entities and demonstrates calling these APIs from two controllers. The application also includes general exception handling and runs on **port 8888**.

## Features

- **Consumes APIs** from the first service (Employee and Department management).
- Two controllers that call external APIs using **RestTemplate**.
- **General exception handling** for better error management.
- **Swagger** API documentation via **Springdoc OpenAPI**.

## Getting Started

### Prerequisites

- **Java 8** or higher
- **Maven**
- First service (CRUD for Employee and Department) running on **port 8080**.

### Running the Application

1. Clone the repository:
    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Build and run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

3. The application will be accessible at:
    ```bash
    http://localhost:8888
    ```

### API Documentation

Swagger UI is available for API documentation at:

[http://localhost:8888/swagger-ui.html](http://localhost:8888/swagger-ui.html)


## Technologies Used

- **Spring Boot** for building the application
- **RestTemplate** for consuming external APIs
- **Springdoc OpenAPI** for Swagger API documentation
- **General Exception Handling** for unified error response
