# Delivery Fee Calculator

Spring Boot application for calculating delivery fees based on city, vehicle type, and weather conditions.

---

## Overview

This project is a solution for the Fujitsu Java Programming Trial Task.

The application:
- Imports weather data from Estonian Environment Agency
- Stores it in a database (H2)
- Calculates delivery fees based on business rules
- Exposes REST API for external usage

---

## Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- OpenAPI (Swagger)
- Lombok

---

## How to Run

### 1. Clone repository

git clone <your-repo-url>
cd delivery-fee

---

### 2. Run application

Maven wrapper:

./mvnw spring-boot:run

---

## API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.htmlW

---

## REST Endpoint

GET /api/v1/delivery-fee

---

## Weather Data Import

Data source: Estonian Environment Agency
URL: https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php
Scheduler: Runs every hour at HH:15

---

## Business Logic

Extra Fees
🌡 Air Temperature
< -10°C → +1 €
-10°C to 0°C → +0.5 €
Wind Speed (Bike only)
10–20 m/s → +0.5 €

20 m/s → forbidden

Weather Phenomenon
Snow / Sleet → +1 €
Rain → +0.5 €
Hail / Thunder / Glaze → forbidden

## 📊 Database

H2 in-memory database is used.

Console:

http://localhost:8080/h2-console
