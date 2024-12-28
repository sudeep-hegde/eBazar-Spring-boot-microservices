# eBazar - Spring Boot Microservices Project

- This is a simple microservices project using Spring Boot 3. eBazar is an API-based microservices application consisting of the following services:
- This project is containerized using Docker, implements Keycloak for authentication, and features centralized logging and monitoring via Grafana and Prometheus. Deployment is managed using Kubernetes and Helm charts.

## Microservices Implemented
1. Product Service
2. Order Service
3. Inventory Service
4. Notification Service
5. API Gateway


## Features:
- Containerized using Docker
- Keycloak for authentication
- Circuit Breaker implemented for both API Gateway and inter-service communication using Resilience4j
- Centralized Logging and Monitoring using Grafana and Prometheus
- Kubernetes Deployment using Helm charts (services such as MySQL, MongoDB, etc. along with applications are configured in the Helm charts)

## Service Overview

### Api-gateway
The API Gateway is responsible for routing requests to the respective services. The following features are implemented in this service:

- Keycloak for API authentication
- Spring Security integration with Keycloak
- Circuit Breaker implementation using Resilience4j
- Centralized Logging (distributed tracing) and Monitoring using Grafana and Prometheus


### Inventory Service
The Inventory Service manages inventory details.

- Database: MySQL
- ORM: Hibernate and JPA


### Order Service 
The Order Service manages order details.

- Circuit Breaker: Implemented using Resilience4j
- Messaging Queue: Kafka for sending order events to the Notification Service


### Notification Service
The Notification Service is responsible for managing notifications.

- Messaging Queue: Kafka for sending notifications to users
- SMTP Server: Sandbox SMTP Mailtrap.io


### Product Service
The Product Service manages product details. 

- Integration Testing: Implemented using Testcontainers (implemented only in this microservice, same can be implemented for other services)
- Database: MongoDB


## How to Run the Project
### Clone the Project
```bash
  git clone https://github.com/sudeep-hegde/eBazar-Spring-boot-microservices.git
```

### Start Dependency Containers
```bash
    cd product-service
    docker-compose up -d

    cd ../order-service
    docker-compose up -d

    cd ../inventory-service
    docker-compose up -d

    cd ../notification-service
    docker-compose up -d

    cd ../api-gateway
    docker-compose up -d
```

### Build and Run the Application Images
```bash
mvn spring-boot:build-image -DskipTests -DdockerPassword=<YourDockerPassword>

docker run -d -p 9000:9000 --name api-gateway <api-gateway-image>:latest
docker run -d -p 8080:8080 --name product-service <product-service-image>:latest
docker run -d -p 8083:8083 --name order-service <order-service-image>:latest
docker run -d -p 8082:8082 --name inventory-service <inventory-service-image>:latest
docker run -d -p 8090:8090 --name notification-service <notification-service-image>:latest
```

### Access the Application
```bash
    Access the API Gateway at:
    http://localhost:9000
```

## Prerequisites
Ensure the following tools are installed on your system:
- Docker
- Maven
- Java 21
- Dependencies specified in docker-compose.yml files for each service are running.
- kind for Kubernetes deployment in local (optional)

## Deployment

- Use provided Helm charts for Kubernetes deployment. (either using kind or any other Kubernetes cluster)
- Deploy both services and dependencies, specified under k8s/manifests/infrastructure and k8s/manifests/applications directories respectively.



