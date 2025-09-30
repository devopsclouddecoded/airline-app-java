# Airline Management System

A Spring Boot application for managing airline flight information with a MySQL database backend.

## Overview

This application provides a RESTful API for managing airline flights. It allows users to:

- Add new flights
- Retrieve flight information
- Update flight details
- Delete flights
- Get a list of all flights with server IP information

## Technology Stack

- **Java 17**
- **Spring Boot 3.0.11**
- **Spring Data JPA**
- **MySQL Database** for production
- **H2 Database** for testing
- **Maven**
- **JUnit 5 & Mockito** for testing
- **JaCoCo** for code coverage (>80%)
- **SonarQube** for code quality analysis
- **Docker** for containerization

## Project Structure

```
airline-app-java-db/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── airline/
│   │   │           ├── controller/
│   │   │           │   └── FlightController.java
│   │   │           ├── model/
│   │   │           │   ├── Flight.java
│   │   │           │   └── FlightWithIP.java
│   │   │           ├── repository/
│   │   │           │   └── FlightRepository.java
│   │   │           ├── service/
│   │   │           │   ├── FlightService.java
│   │   │           │   └── impl/
│   │   │           │       └── FlightServiceImpl.java
│   │   │           └── AirlineApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── airline/
│                   ├── controller/
│                   │   └── FlightControllerTest.java
│                   └── AirlineApplicationTests.java
├── Dockerfile
├── pom.xml
└── README.md
```

## Flight Model

The Flight entity contains the following fields:
- `flightId` - Unique identifier for the flight
- `flightName` - Name of the flight
- `source` - Departure location
- `Destination` - Arrival location
- `ticketPrice` - Price of the ticket

## API Endpoints

| Method | URL                   | Description                   | Request Body | Response                      |
|--------|------------------------|-------------------------------|--------------|-------------------------------|
| POST   | `/flight/`             | Add a new flight              | Flight object | Created flight with 201 status |
| GET    | `/flight/`             | Get all flights with server IP | None         | List of flights with server IP |
| GET    | `/flight/{flightId}`   | Get a specific flight         | None         | Flight object                 |
| PUT    | `/flight/{flightId}`   | Update a flight               | Flight object | Updated flight                |
| DELETE | `/flight/{flightId}`   | Delete a flight               | None         | Success message with flight ID |

## Running the Application

### Prerequisites
- Java 17 or higher
- MySQL Server
- Maven

### Configuration

Database configuration can be modified in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/airline?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

### Running Locally

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
   ```
   mvn clean package
   ```
4. Run the application:
   ```
   java -jar target/airline-app.jar
   ```

The application will start on port 9000.

### Running with Docker

1. Build the Docker image:
   ```
   docker build -t airline-app .
   ```

2. Run the container:
   ```
   docker run -p 9000:9000 airline-app
   ```

## Testing

The application includes comprehensive unit tests for both controller and service layers. Tests are run using an H2 in-memory database to ensure isolation and fast execution.

Run tests with:

```
mvn test
```

### Code Coverage

Code coverage is measured using JaCoCo. The project is configured to maintain a minimum of 80% code coverage. To generate a coverage report:

```
mvn clean test jacoco:report
```

The coverage report will be available in `target/site/jacoco/index.html`.

To verify that code coverage meets the 80% threshold:

```
mvn jacoco:check
```

## Code Quality Analysis with SonarQube

The project is configured for SonarQube integration to monitor code quality and test coverage.

### Prerequisites

- SonarQube server running (locally or remote)
- SonarQube token for authentication

### Running SonarQube Analysis

```
mvn clean verify sonar:sonar -Dsonar.login=your_sonar_token
```

Alternatively, set the token as an environment variable:

```
export SONAR_TOKEN=your_sonar_token
mvn clean verify sonar:sonar
```

## Sample Database Queries

You can use the following SQL queries to add sample data to your database:

### Create the Database

```sql
CREATE DATABASE IF NOT EXISTS airline;
USE airline;
```

### Create the Flight Table

```sql
CREATE TABLE IF NOT EXISTS flight (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_name VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    ticket_price DOUBLE NOT NULL
);
```

### Insert Sample Data

```sql
INSERT INTO flight (flight_name, source, destination, ticket_price) VALUES
('AI101', 'Delhi', 'Mumbai', 5000.00),
('SG202', 'Mumbai', 'Bangalore', 4500.00),
('UK303', 'Chennai', 'Kolkata', 6000.00),
('IX404', 'Hyderabad', 'Delhi', 5500.00),
('AI505', 'Bangalore', 'Chennai', 3500.00);
```

### Query Examples

```sql
-- Get all flights
SELECT * FROM flight;

-- Get a specific flight by ID
SELECT * FROM flight WHERE flight_id = 1;

-- Get flights by source
SELECT * FROM flight WHERE source = 'Mumbai';

-- Get flights by destination
SELECT * FROM flight WHERE destination = 'Delhi';

-- Get flights with ticket price less than 5000
SELECT * FROM flight WHERE ticket_price < 5000;
```

## API Usage Examples

Here are some examples of how to interact with the API using curl commands:

### Get All Flights

```bash
curl -X GET http://localhost:9095/flight/
```

### Get a Specific Flight

```bash
curl -X GET http://localhost:9095/flight/1
```

### Add a New Flight

```bash
curl -X POST http://localhost:9095/flight/ \
  -H "Content-Type: application/json" \
  -d '{
    "flightName": "AI707",
    "source": "Pune",
    "destination": "Goa",
    "ticketPrice": 3800.00
  }'
```

### Update a Flight

```bash
curl -X PUT http://localhost:9095/flight/1 \
  -H "Content-Type: application/json" \
  -d '{
    "flightName": "AI101",
    "source": "New Delhi",
    "destination": "Mumbai",
    "ticketPrice": 5200.00
  }'
```

### Delete a Flight

```bash
curl -X DELETE http://localhost:9095/flight/1
```

## Logging

The application uses SLF4J with Logback for logging. The logging configuration is defined in `src/main/resources/logback-spring.xml`. Logs are written to both the console and a file.

### Log Levels

- **INFO**: General application events and milestones
- **DEBUG**: Detailed information for debugging purposes
- **ERROR**: Error events that might still allow the application to continue running

### Log Files

Log files are stored in the `logs` directory:
- Current log file: `logs/airline-app.log`
- Archived logs: `logs/archived/airline-app-yyyy-MM-dd.i.log`

Logs are rotated daily and when they reach 10MB in size. A maximum of 30 days of logs are kept.

## Development Notes

- The application uses Spring Data JPA for database operations
- Exception handling is implemented for flight not found scenarios
- The application returns the server IP address along with flight information
- Tests use H2 in-memory database for isolation and speed
- Code coverage is maintained above 80%
- Port 9095 is used for the application
- Comprehensive logging is implemented throughout the application
