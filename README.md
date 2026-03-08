# saga-pattern-spring-boot-demo

Demonstration of SAGA Orchestration Design Pattern using Java 17 / Spring Boot 3.2.5 and Kafka 4.1.0

## Quick Start

Before running the project, you need to build the `core` module first (it contains shared code and is used by other modules).

The easiest and recommended way is to run this command **from the root of the project**:

```bash
cd core
mvn clean install
cd ..