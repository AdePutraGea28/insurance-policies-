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


# With docker
- docker compose up -d db

- docker exec -it insurance-postgres psql -U insurance_user -d insurance_db

- Buat Tabel
CREATE TABLE public."policy" (
	id serial4 NOT NULL,
	holder_name varchar(255) NOT NULL,
	"type" varchar(100) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	premium_amount numeric(15, 2) NOT NULL,
	created_by varchar(25) NULL,
	updated_by varchar(25) NULL,
	created_date timestamp NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT policy_pkey PRIMARY KEY (id)
);

- keluar dari database

- jalankan command docker compose up -d app

- docker compose up --build

- kemudian test api dengan swagger http://localhost:8082/insurancepolicy/swagger-ui/index.html
