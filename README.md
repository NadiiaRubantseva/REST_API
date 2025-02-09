# **Item Management REST API**

## 📌 **Project Overview**
This project is a **RESTful API** for managing items, designed with **Spring Boot**. It supports **CRUD operations** and includes **role-based authentication (Admin/User)** to control access to different endpoints.

## 🛠 **Features**
✅ **CRUD Operations**
✅ **Role-Based Access Control (RBAC)** using JWT authentication  
✅ **RESTful API Design** following best practices  
✅ **Comprehensive API Documentation** via Postman

---
## 📖 **API Documentation**
The API is fully documented in **Postman**. You can access the documentation [here](https://documenter.getpostman.com/view/23244926/2sAYX9kzgY#38b23cfc-00b0-478a-b1c4-bd48f637a7b4).

---

## **To Run the Application**
Using Gradle Wrapper:
```sh
./gradlew bootRun
```
Or using an IDE (IntelliJ, Eclipse, etc.).

---

## 🔑 **Authentication & Authorization**
The API uses **JWT authentication**. Users must log in to get a token and use it in API requests.

### **User Roles:**
- **Admin** 🛠️ – Can create, update, delete, and retrieve items.
- **User** 👤 – Can only retrieve item details.

---

## 👨‍💻 **Endpoints Summary**

### **Item Management** (`/api/v1/items`)
| Method  | Endpoint          | Role  | Description               |
|---------|------------------|-------|---------------------------|
| `GET`   | `/`              | Admin, User | Retrieve all items |
| `POST`  | `/`              | Admin | Create a new item |
| `GET`   | `/{id}`          | Admin, User | Retrieve item by ID |
| `PUT`   | `/{id}`          | Admin | Replace an existing item |
| `PATCH` | `/{id}`          | Admin | Update part of an item |
| `DELETE`| `/{id}`          | Admin | Delete an item |

---

## 🛠 **Technologies Used**
- **Spring Boot** – Backend Framework
- **Spring Security & JWT** – Authentication & Authorization
- **H2** – Database
- **Lombok** – Reduces boilerplate code
- **Gradle** – Dependency Management
- **Postman** – API Testing & Documentation

---

## 🏗 **Future Improvements**
✅ Add **User Registration & Logout** functionality  
