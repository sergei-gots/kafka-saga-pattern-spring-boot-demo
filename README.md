# Saga-pattern-spring-boot-demo

Demonstration of SAGA Orchestration Design Pattern using:
<li>Java 17 
<li>Spring Boot 3.2.5 
<li>Kafka 4.1.0

## Saga Orchestration Demo – Step by Step

This project shows how to build a real-world **orchestration-based saga** for placing orders in a microservices system.

**How it's structured:**  
Every commit is one small, understandable step toward the complete saga implementation.

Want to learn sagas properly?  
Just follow the commit history - start from the beginning and see how everything comes together.


## Quick Start

### Launch Kafka cluster

The Kafka cluster is defined in `docker-compose.yml` at the project root.

Run in the root directory:

```bash
docker compose up -d
```
Verify Kafka is running:
```bash
docker compose ps
```

You should see healthy containers `kafka-1, kafka-2, kafka-3`.

### 'core' module
Before running the project and each time you make changes in the `core` module, 
you may need to build the `core` module first - it contains shared code 
and is used by other modules.

The easiest and recommended way is to run this command **from the root of the project**:

```bash
cd core
mvn clean install
cd ..
```

### IDE Setup (IntelliJ IDEA)

For seamless development with Lombok you can apply the following setting in this multi-module project:

- Open **Settings** (Ctrl+Alt+S)
- Navigate to **Build, Execution, Deployment → Build Tools → Maven → Runner**
- Enable **Delegate IDE build/run actions to Maven**

This ensures Maven handles compilation (including Lombok annotation processing) even when launching applications directly from the IDE.


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