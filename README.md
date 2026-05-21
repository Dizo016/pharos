# Pharos — Library Management System

> A full-stack web application for managing library collections, members, and loans.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=flat-square&logo=springboot)
![React](https://img.shields.io/badge/React-19.2.6-61DAFB?style=flat-square&logo=react)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-336791?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)

---

## About

Pharos is a library management platform that allows institutions to catalog their book collections, register members, and control the full loan lifecycle — including renewals, returns, and overdue tracking.

---

## Features

- **Book catalog** — add, edit, search, and deactivate books with copy tracking
- **Member management** — register and manage library members
- **Loan control** — issue loans, process returns, renew due dates, and flag overdue items
- **Dashboard** — real-time statistics on volumes, active loans, members, and overdue items
- **Authentication** — JWT-based login with role-based access (Admin / User)
- **User management** — admins can create, update, and deactivate system users

---

## Tech Stack

| Layer      | Technology                                      |
|------------|-------------------------------------------------|
| Backend    | Java 17, Spring Boot 4.0.2, Spring Security     |
| Database   | PostgreSQL 17, Flyway migrations, Spring Data JPA |
| Auth       | JWT (java-jwt 4.4.0), BCrypt                    |
| Frontend   | React 19.2.6, Vite, React Router, Axios         |
| Infra      | Docker, Docker Compose                          |

---

## Prerequisites

- [Java 17+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [Node.js 18+](https://nodejs.org/)
- [Docker & Docker Compose](https://docs.docker.com/get-docker/) *(optional, for the quick start)*

---

## Getting Started

### Option 1 — Docker (recommended)

Starts the database and backend together:

```bash
docker-compose up
```

Then start the frontend separately:

```bash
cd frontend
npm install
npm run dev
```

App available at `http://localhost:5173`.

---

### Option 2 — Manual

#### 1. Database

Start a PostgreSQL 17 instance and create a database named `pharos`.

#### 2. Backend

```bash
cd backend

# Copy and fill in the environment file
cp .env.example .env

mvn clean install
mvn spring-boot:run
```

Backend runs on `http://localhost:8080`.

#### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:5173`.

---

## Environment Variables

Create a `.env` file inside `backend/` based on `.env.example`:

| Variable      | Description                              | Example                                    |
|---------------|------------------------------------------|--------------------------------------------|
| `DB_URL`      | JDBC connection string                   | `jdbc:postgresql://localhost:5432/pharos`  |
| `DB_NAME`     | Database name                            | `pharos`                                   |
| `DB_USERNAME` | Database user                            | `postgres`                                 |
| `DB_PASSWORD` | Database password                        | `admin123`                                 |
| `JWT_SECRET`  | Secret key for signing tokens (min 32 chars) | `your_super_secret_key_here`           |

Frontend API URL can be configured in `frontend/.env`:

```
VITE_API_URL=http://localhost:8080
```

---

## Project Structure

```
pharos/
├── backend/
│   └── src/main/
│       ├── java/br/com/pharos/
│       │   ├── auth/          # JWT filter, security config
│       │   ├── book/          # Book entity, service, controller
│       │   ├── member/        # Member entity, service, controller
│       │   ├── loan/          # Loan entity, service, controller
│       │   ├── user/          # User entity, service, controller
│       │   └── dashboard/     # Dashboard stats endpoint
│       └── resources/
│           ├── application.yaml
│           └── db/migration/  # Flyway SQL migrations (V1–V5)
├── frontend/
│   └── src/
│       ├── components/        # Reusable UI components
│       ├── contexts/          # AuthContext (JWT + user state)
│       ├── pages/             # Landing, Login, Register, Home
│       └── services/          # Axios instances and API calls
└── docker-compose.yml
```

---

## Database Migrations

Migrations are managed by Flyway and run automatically on startup:

| Version | Description               |
|---------|---------------------------|
| V1      | Create `users` table      |
| V2      | Create `members` table    |
| V3      | Create `books` table      |
| V4      | Create `loans` table      |
| V5      | Seed initial data         |

---

## API Reference

### Auth

| Method | Endpoint    | Auth     | Description           |
|--------|-------------|----------|-----------------------|
| POST   | `/auth/login` | Public | Login, returns JWT    |
| GET    | `/auth/me`  | Required | Get current user info |

### Books

| Method | Endpoint                        | Description              |
|--------|---------------------------------|--------------------------|
| GET    | `/books`                        | List all books           |
| GET    | `/books/available`              | List available books     |
| GET    | `/books/{id}`                   | Get book by ID           |
| GET    | `/books/search/title?title=`    | Search by title          |
| GET    | `/books/search/author?author=`  | Search by author         |
| GET    | `/books/category/{category}`    | Filter by category       |
| POST   | `/books`                        | Create book              |
| PUT    | `/books/{id}`                   | Update book              |
| DELETE | `/books/{id}`                   | Deactivate book          |

### Members

| Method | Endpoint                  | Description           |
|--------|---------------------------|-----------------------|
| GET    | `/members`                | List all members      |
| GET    | `/members/{id}`           | Get member by ID      |
| GET    | `/members/search?name=`   | Search by name        |
| POST   | `/members`                | Create member         |
| PUT    | `/members/{id}`           | Update member         |
| DELETE | `/members/{id}`           | Deactivate member     |

### Loans

| Method | Endpoint                          | Description                  |
|--------|-----------------------------------|------------------------------|
| GET    | `/loans`                          | List all loans               |
| GET    | `/loans/overdue`                  | List overdue loans           |
| GET    | `/loans/{id}`                     | Get loan by ID               |
| GET    | `/loans/member/{memberId}`        | Loans by member              |
| GET    | `/loans/book/{bookId}`            | Loans by book                |
| GET    | `/loans/status/{status}`          | Filter by status             |
| POST   | `/loans`                          | Create loan                  |
| PATCH  | `/loans/{id}/return`              | Mark as returned             |
| PATCH  | `/loans/{id}/renew?newDueDate=`   | Renew loan                   |

### Users *(Admin only)*

| Method | Endpoint      | Description         |
|--------|---------------|---------------------|
| GET    | `/users`      | List all users      |
| GET    | `/users/{id}` | Get user by ID      |
| POST   | `/users`      | Create user (public)|
| PUT    | `/users/{id}` | Update user         |
| DELETE | `/users/{id}` | Deactivate user     |

### Dashboard

| Method | Endpoint     | Description                        |
|--------|--------------|------------------------------------|
| GET    | `/dashboard` | Total volumes, loans, members, overdue |

---

## Roles & Permissions

| Resource    | USER | ADMIN |
|-------------|------|-------|
| Books       | ✅   | ✅    |
| Members     | ✅   | ✅    |
| Loans       | ✅   | ✅    |
| Dashboard   | ✅   | ✅    |
| User management | ❌ | ✅  |

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feat/your-feature`
3. Commit your changes following [Conventional Commits](https://www.conventionalcommits.org/)
4. Open a pull request targeting `main`

---

## Author

**Diego Silva** — [diegossilva2008@gmail.com](mailto:diegossilva2008@gmail.com)

---

## License

This project is licensed under the MIT License.
