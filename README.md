<<<<<<< HEAD
# SpringBoot Login System

Spring Boot app with secure login, registration, password updates, and dashboard. Uses Spring Security, H2 DB with JPA, Bootstrap 5.3.3, and Thymeleaf. Perfect for learning authentication and persistence. Run with `mvn spring-boot:run`. Access at `localhost:8080/login`.

## Overview

`Springboot login system` is a web application built with Spring Boot 3.4.3, showcasing a secure user authentication system. It allows users to:
- **Register** with a unique username and password.
- **Log in** securely, with a default admin user ("Ian", password: "1234").
- **Update passwords**, persisting changes in an H2 database.
- **Access a dashboard** with a personalized greeting.
- **Log out** securely.

The app leverages Spring Security for authentication, Spring Data JPA with H2 for data persistence, and Bootstrap 5.3.3 with Thymeleaf for a modern, responsive UI.

## Features

- **Registration**: Sign up with a username and password (default role: "USER").
- **Login**: Secure authentication with error feedback.
- **Password Management**: Change passwords for logged-in users.
- **Dashboard**: Personalized welcome page post-login.
- **Persistent Storage**: H2 database (in-memory or file-based).
- **Styling**: Bootstrap 5.3.3 via Webjars for a polished look.

## Prerequisites

- **Java 20**: Ensure JDK 20 is installed (`java -version`).
- **Maven**: Required for building and running (`mvn -v`).
- **Git**: To clone the repository.

## Setup

1. **Clone the Repository**:
   ```bash
   git clone [https://github.com/yourusername/loginwithspring.git](https://github.com/IKiprotich/Springboot-Login-System.git)
   cd Springboot login system
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the App**:
   - Open `http://localhost:8080/login` in a browser.

## Usage

- **Login**:
  - Default admin: Username: `Ian`, Password: `1234`.
  - Or register a new user at `http://localhost:8080/register`.

- **Register**:
  - Visit `http://localhost:8080/register`, enter a username and password, then log in.

- **Change Password**:
  - From `http://localhost:8080/home`, click "Change Password", update, and re-login.

- **Logout**:
  - Click "Logout" on the dashboard to return to the login page.

- **Database** (Optional):
  - Inspect at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, password: blank).

## Configuration

Edit `src/main/resources/application.properties` for custom settings:
- **Persistent DB**: Change `spring.datasource.url=jdbc:h2:file:./data/users` to save data across restarts.
- **Port**: Set `server.port=8081` to run on a different port.

## Project Structure

- `pom.xml`: Maven configuration with dependencies.
- `src/main/java/com/example/loginwithspring/config/SecurityConfig.java`: Core logic (security, routes, DB ops).
- `src/main/java/com/example/loginwithspring/model/AppUser.java`: User entity.
- `src/main/java/com/example/loginwithspring/repository/UserRepository.java`: JPA repository.
- `src/main/resources/templates/`: Thymeleaf templates (`login.html`, `home.html`, etc.).

## Dependencies

- Spring Boot 3.4.3 (`spring-boot-starter-*`)
- Spring Security
- Thymeleaf with Spring Security Extras
- Bootstrap 5.3.3 (Webjars)
- Spring Data JPA
- H2 Database

## Contributing

1. Fork the repo.
2. Create a branch (`git checkout -b feature/your-feature`).
3. Commit changes (`git commit -m "Add your feature"`).
4. Push to your fork (`git push origin feature/your-feature`).
5. Open a Pull Request.

Suggestions: Add email to user registration, switch to MySQL, or enhance UI.

## License

MIT License - feel free to use, modify, and distribute.

## Contact

For issues or ideas, open a GitHub issue or reach out to @ctrliann.

=======
This is a sample login page using springboot 3.1.0 and Thymeleaf view engine. Session handling and cookies are implemented using spring security. Feel free to pull request and update the code for readability.
>>>>>>> 8f65402 (Spring boot login Web App)
