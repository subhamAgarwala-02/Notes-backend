# 📝 Notes App - Spring Boot Backend

This is a simple and secure Notes App backend built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **PostgreSQL**. It allows users to register, log in, and manage their personal notes with proper authentication and authorization.

---

## 🚀 Features

- ✅ User Signup & Login with JWT Authentication
- ✅ Role-based access control
- ✅ Create, Read, Update, and Delete (CRUD) operations on notes
- ✅ Secure password handling with BCrypt
- ✅ Token-based stateless session management
- ✅ Clean RESTful API design
- ✅ Swagger UI for API documentation

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Swagger UI** (via Springdoc OpenAPI)

---

## 📦 Project Structure

src/
└── main/
├── java/
│ └── com/subham/Notes_backend/
│ ├── controller/
│ ├── model/
│ ├── repository/
│ ├── service/
│ ├── config/
│ └── filter/
└── resources/
└── application.properties

yaml
Copy
Edit

---

## 🔐 Authentication

This project uses **JWT (JSON Web Token)** for secure stateless authentication.

Tokens are issued during login and must be included in the `Authorization` header for protected endpoints:

Authorization: Bearer <your-token>

yaml
Copy
Edit

---

## ⚙️ Getting Started

### ✅ Prerequisites

- Java 17+
- Maven
- PostgreSQL installed and running

---

### 🔧 Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/subhamAgarwala-02/Notes-full-stack.git
   cd Notes-full-stack
Configure PostgreSQL
Update your application.properties or application.yml:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
jwt.secret=your_jwt_secret
Run the application

bash
Copy
Edit
mvn spring-boot:run
Access APIs: http://localhost:8080/api
Access Swagger UI: http://localhost:8080/swagger-ui.html

📄 API Endpoints
Method	Endpoint	Description	Auth Required
POST	/auth/signUp	Register new user	❌
POST	/auth/login	Authenticate & get JWT token	❌
GET	/api/notes	Get all notes	✅
POST	/api/notes	Add new note	✅
PUT	/api/notes/{id}	Update a note by ID	✅
DELETE	/api/notes/{id}	Delete a note by ID	✅

🔍 Swagger UI
Test your APIs visually using Swagger at:

📌 http://localhost:8080/swagger-ui.html

Go to the Swagger UI URL.

Click the "Authorize" button.

Paste your JWT token like:

php-template
Copy
Edit
Bearer <your_token_here>
Now you can interact with all secured endpoints.

💡 Notes
Passwords are securely hashed using BCrypt.

Stateless JWT is used for authentication (no session stored on server).

Swagger excludes password fields from being exposed for security.

@RestControllerAdvice is used for exception handling (if implemented).

🔄 Pushing Changes to GitHub
bash
Copy
Edit
git add .
git commit -m "Updated backend with JWT, Swagger & PostgreSQL config"
git pull --rebase origin main
git push origin main
👨‍💻 Author
Subham Agarwala
GitHub • LinkedIn

🌟 Star the repo if you found this helpful! PRs welcome!

yaml
Copy
Edit

---

Let me know if you also want me to create a corresponding `Postman Collection` or show how to run this with Docker or Docker Compose.
