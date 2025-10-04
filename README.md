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

## API Documentation

- Authentication

  - POST `/api/auth/register` - Register new user
  - POST `/api/auth/login` - Login user

- Movies

  - GET `/api/movies` - List all movies
  - GET `/api/movies/{id}` - Get movie details
  - POST `/api/movies` - Add new movie (Admin)
  - DELETE `/api/movies/{id}` - Delete movie (Admin)

- Theaters

  - GET `/api/theaters` - List all theaters
  - GET `/api/theaters/{id}` - Get theater details

- Shows

  - GET `/api/shows/movie/{movieId}` - Get shows for movie
  - POST `/api/shows` - Add new show (Admin)

- Bookings
  - POST `/api/bookings` - Create booking
  - GET `/api/bookings/my-bookings` - Get user bookings

## Security

All endpoints except authentication endpoints require JWT token in Authorization header:

```
Authorization: Bearer <token>
```
