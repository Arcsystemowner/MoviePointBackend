# MoviePoint Backend

Backend service for MoviePoint application built with Spring Boot.

## Technologies Used

- Java 21
- Spring Boot 3.2.x
- PostgreSQL
- JWT Authentication
- Spring Security

## Setup Instructions

1. Clone the repository

```bash
git clone https://github.com/Arcsystemowner/MoviePointBackend.git
```

2. Create application.properties

```bash
cp src/main/resources/application.properties.template src/main/resources/application.properties
```

3. Update application.properties with your database and JWT configuration

4. Build the project

```bash
./mvnw clean install
```

5. Run the application

```bash
./mvnw spring-boot:run
```

## Environment Setup

1. Create a `.env` file in the root directory:

```bash
cp .env.example .env
```

2. Update the `.env` file with your configuration:

- `DB_PASSWORD`: Your PostgreSQL database password
- `JWT_SECRET`: A secure random string for JWT token signing
- `POSTGRES_DB`: Database name (default: moviepoint)
- `POSTGRES_USER`: Database username (default: postgres)
- `POSTGRES_PORT`: Database port (default: 5432)
- `SERVER_PORT`: Application port (default: 8080)

## User Roles

The system has three types of users:

1. **Admin** - System administrator with full access
2. **Theater Owner** - Manages theaters and shows
3. **User** - Regular user who can book tickets

## API Documentation

### Authentication

| Method | Endpoint                   | Description            | Access |
| ------ | -------------------------- | ---------------------- | ------ |
| POST   | `/api/auth/register`       | Register new user      | Public |
| POST   | `/api/auth/login`          | Login user             | Public |
| POST   | `/api/auth/register-owner` | Register theater owner | Admin  |

### Movies

| Method | Endpoint           | Description       | Access |
| ------ | ------------------ | ----------------- | ------ |
| GET    | `/api/movies`      | List all movies   | Public |
| GET    | `/api/movies/{id}` | Get movie details | Public |
| POST   | `/api/movies`      | Add new movie     | Admin  |
| PUT    | `/api/movies/{id}` | Update movie      | Admin  |
| DELETE | `/api/movies/{id}` | Delete movie      | Admin  |

### Theaters

| Method | Endpoint                        | Description          | Access        |
| ------ | ------------------------------- | -------------------- | ------------- |
| GET    | `/api/theaters`                 | List all theaters    | Public        |
| GET    | `/api/theaters/{id}`            | Get theater details  | Public        |
| POST   | `/api/theaters`                 | Add new theater      | Theater Owner |
| PUT    | `/api/theaters/{id}`            | Update theater       | Theater Owner |
| GET    | `/api/theaters/owner/{ownerId}` | Get owner's theaters | Theater Owner |
| DELETE | `/api/theaters/{id}`            | Delete theater       | Theater Owner |

### Shows

| Method | Endpoint                         | Description           | Access        |
| ------ | -------------------------------- | --------------------- | ------------- |
| GET    | `/api/shows/movie/{movieId}`     | Get shows for movie   | Public        |
| GET    | `/api/shows/theater/{theaterId}` | Get shows for theater | Public        |
| POST   | `/api/shows`                     | Add new show          | Theater Owner |
| PUT    | `/api/shows/{id}`                | Update show           | Theater Owner |
| DELETE | `/api/shows/{id}`                | Delete show           | Theater Owner |

### Bookings

| Method | Endpoint                            | Description          | Access        |
| ------ | ----------------------------------- | -------------------- | ------------- |
| POST   | `/api/bookings`                     | Create booking       | User          |
| GET    | `/api/bookings/my-bookings`         | Get user's bookings  | User          |
| GET    | `/api/bookings/theater/{theaterId}` | Get theater bookings | Theater Owner |
| POST   | `/api/bookings/{id}/cancel`         | Cancel booking       | User          |

## Security

### Authentication

All protected endpoints require JWT token in Authorization header:

```
Authorization: Bearer <token>
```

### Role-Based Access

- **Public** - No authentication required
- **User** - Requires authenticated user
- **Theater Owner** - Requires theater owner role
- **Admin** - Requires admin role

### Default Credentials

```
Admin:
- Email: admin@moviepoint.com
- Password: admin123

Theater Owner:
- Email: owner@theater.com
- Password: owner123

User:
- Email: user@example.com
- Password: user123
```

## Error Responses

| Status | Description  |
| ------ | ------------ |
| 200    | Success      |
| 400    | Bad Request  |
| 401    | Unauthorized |
| 403    | Forbidden    |
| 404    | Not Found    |
| 500    | Server Error |
