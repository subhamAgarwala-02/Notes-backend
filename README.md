# 📝 Notes App - Spring Boot Backend

This is a simple and secure Notes App backend built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **PostgreSQL**. It allows users to register, log in, and manage their personal notes with proper authentication and authorization.

---

## 🚀 Features

- User Signup & Login with JWT Authentication
- Role-based access control
- Create, Read, Update, and Delete (CRUD) operations on notes
- Secure password handling with BCrypt
- Token-based stateless session management
- Clean RESTful API design

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

---

## 📦 Project Structure

src/
└── main/
├── java/
│ └── com/example/notesapp/
│ ├── controller/
│ ├── model/
│ ├── repository/
│ ├── service/
│ ├── config/
│ └── security/
└── resources/
├── application.properties

yaml
Copy
Edit

---

## 🔐 Authentication

This project uses **JWT** (JSON Web Token) for secure stateless authentication. Tokens are issued during login and must be included in the `Authorization` header for protected endpoints:

Authorization: Bearer <token>

yaml
Copy
Edit

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/notes-app.git
   cd notes-app
Configure PostgreSQL
Update your application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
Run the application

bash
Copy
Edit
mvn spring-boot:run
Access APIs at: http://localhost:8080/api

🔄 API Endpoints (Sample)
Method	Endpoint	Description
POST	/api/auth/signup	Register new user
POST	/api/auth/login	Authenticate and get JWT
GET	/api/notes	Get all notes (Auth)
POST	/api/notes	Add a new note (Auth)
PUT	/api/notes/{id}	Update note (Auth)
DELETE	/api/notes/{id}	Delete note (Auth)
