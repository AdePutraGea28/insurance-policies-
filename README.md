# 🛡️ Insurance Policy API

RESTful API sederhana untuk mengelola data polis asuransi menggunakan **Spring Boot**, **PostgreSQL**, dan **Docker**.

---

## 📌 Features

- Create, Read, Update, Delete (CRUD) Insurance Policies
- Exception handling dengan custom exception
- Unit testing pada service layer
- Containerization menggunakan Docker & Docker Compose

---

## 🧰 Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- JUnit + Mockito
- Maven
- Docker

## 📥 Clone Project

```bash
git clone https://github.com/AdePutraGea28/insurance-policies-.git
cd insurance-policies

⚙️ Konfigurasi Database

🔸 Buat Database Baru
Jalankan script yang ada di file berikut "src/main/resources/sql/create-table-polic-a-26072025.sql"

🔸 Update application.properties
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=adeputragea
spring.datasource.password=

🚀 Jalankan Project
📦 Build
mvn clean install

▶️ Jalankan
mvn spring-boot:run

✅ Akses Swagger API
http://localhost:8082/insurancepolicy/swagger-ui/index.html

🧪 Jalankan Unit Test
mvn test

📌 Contoh API
🔹 Create Policy
POST /api/policies
curl -X 'POST' \
  'http://localhost:8082/insurancepolicy/api/policy/create' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "holderName": "string",
  "type": "string",
  "startDate": "2025-07-26",
  "endDate": "2025-07-26",
  "premiumAmount": 0
}'

🔹 Get by ID
GET /api/policies/{id}
curl -X 'GET' \
  'http://localhost:8082/insurancepolicy/api/policy/getById/1' \
  -H 'accept: application/json'

🔹 Update
PUT /api/policies/{id}
curl -X 'PUT' \
  'http://localhost:8082/insurancepolicy/api/policy/update/1' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "holderName": "string",
  "type": "string",
  "startDate": "2025-07-26",
  "endDate": "2025-07-26",
  "premiumAmount": 0
}'

🔹 Get All Policies
GET /api/policies
curl -X 'GET' \
  'http://localhost:8082/insurancepolicy/api/policy/getAll' \
  -H 'accept: application/json'