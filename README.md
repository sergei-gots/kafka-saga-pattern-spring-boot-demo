# saga-pattern-spring-boot-demo

Demonstration of SAGA Orchestration Design Pattern using Java 17 / Spring Boot 3.2.5 and Kafka 4.1.0

## Quick Start

Before running the project, you need to build the `core` module first (it contains shared code and is used by other modules).

The easiest and recommended way is to run this command **from the root of the project**:

```bash
cd core
mvn clean install
cd ..
```

## API Documentation (Swagger UI)

This project contains multiple microservices with REST APIs, each equipped with interactive OpenAPI/Swagger documentation powered by **springdoc-openapi**.

After starting the corresponding service, you can access the Swagger UI in your browser:

| Service            | Default Port | Swagger UI URL                                      |
|--------------------|--------------|-----------------------------------------------------|
| orders-service     | 8080         | http://localhost:8080/swagger-ui/index.html         |
| products-service   | 8081         | http://localhost:8081/swagger-ui/index.html         |

Alternative URLs (both usually work):
- `http://localhost:<port>/swagger-ui.html`
- `http://localhost:<port>/swagger-ui/index.html`

You can also access the raw OpenAPI JSON specification at:
- `http://localhost:<port>/v3/api-docs`