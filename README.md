# Merchant Microservice Management System

A backend microservice-based application developed using Spring Boot to manage merchant data and their contact details. It provides REST APIs to perform CRUD operations, search, and filter merchant records, and is structured using layered architecture. The project is unit-tested with JUnit & Mockito and integrates PostgreSQL as the database.

---

## 🧱 Tech Stack Used

- **Backend:** Java 17, Spring Boot 3, Spring JPA, Lombok
- **Database:** PostgreSQL
- **Testing:** JUnit, Mockito, MockMvc
- **Build Tool:** Maven
- **API Testing:** cURL / pgAdmin (Postman blocked)
- **Version Control:** Git + GitHub
- **Future Scope:** React.js frontend, Docker, AWS EC2 deployment, CI/CD pipeline

---

## 📦 Entities

- **Merchant**: Merchant ID (PK), DBA, Address, URL
- **Phone**: Contact Number, Contact Type (Mobile, Landline, Fax, WhatsApp, UPI)
- **Email**: Email ID, Email Classification (Primary, Secondary, Additional)

---

## 🔍 Key Features

- Full CRUD operations for Merchant, Phone, and Email
- Entity-level validation using `@NotNull`, `@Pattern`, `@Email`
- Relationship mapping (One-to-Many, Many-to-One)
- Search merchants by address
- Filter contacts by type (e.g., only Mobile and Fax)
- JUnit & Mockito tests for service and controller layers
- API response validation using pgAdmin query tool

---

## 🧪 Testing

- **Service Layer** tested using JUnit + Mockito
- **Controller Layer** tested using MockMvc
- End-to-end integration testing with real DB interactions

---

## 🌐 Sample API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/merchants` | Create a new merchant |
| `GET` | `/merchants/{id}` | Get merchant by ID |
| `PUT` | `/merchants/{id}` | Update merchant |
| `DELETE` | `/merchants/{id}` | Delete merchant |
| `GET` | `/merchants/address/{city}` | Filter merchants by address |
| `GET` | `/contacts/type/{type}` | Get contacts filtered by type (e.g., Mobile, Fax) |

---

## ✅ Execution Phases Followed

1. Project Setup & DB Configuration
2. Entity Definition & Validation
3. Repository & Service Layer
4. Controller Development & REST APIs
5. Unit Testing with JUnit & Mockito
6. Integration Testing & GitHub Deployment

---

## 👩‍💻 Author

Built with ❤️ by Shruti

[LinkedIn](https://www.linkedin.com/in/shruti-pacharne-57b354218)