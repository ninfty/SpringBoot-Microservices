## Docker

```
# Keycloak
docker run --name msjava-keycloak -p 8081:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin --network msjava-network quay.io/keycloak/keycloak:26.0.7 start-dev

# RabbitMQ
docker run -it --name msjava-rabbitmq -p 5672:5672 -p 15672:15672 --network msjava-network rabbitmq:4.0-management

# Network
docker network create msjava-network

# Eureka
docker build --tag msjava-eureka .
docker run --name msjava-eureka -p 8761:8761 --network msjava-network msjava-eureka

# Credit Cards MS
docker build --tag msjava-creditcards .
docker run --name msjava-creditcards --network msjava-network -e RABBITMQ_SERVER=msjava-rabbitmq -e EUREKA_SERVER=msjava-eureka -d msjava-creditcards

# Customers MS
docker build --tag msjava-customers .
docker run --name msjava-customers --network msjava-network -e RABBITMQ_SERVER=msjava-rabbitmq -e EUREKA_SERVER=msjava-eureka -d msjava-customers

# Credit Assessment MS
docker build --tag msjava-creditassessment .
docker run --name msjava-creditassessment -P --network msjava-network -e RABBITMQ_SERVER=msjava-rabbitmq -e EUREKA_SERVER=msjava-eureka -d msjava-creditassessment

# Gateway
docker build --tag msjava-gateway .
docker run --name msjava-gateway -p 8080:8080 -e EUREKA_SERVER=msjava-eureka -e KEYCLOAK_SERVER=msjava-keycloak -e KEYCLOAK_PORT=8080 --network msjava-network -d msjava-gateway
```