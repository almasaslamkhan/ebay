# Calculator Spring Boot Application

A simple calculator application built with Spring Boot that provides RESTful APIs for basic arithmetic operations and supports CRUD operations for calculations. The application uses an in-memory H2 database to store calculation history and demonstrates exception handling and data validation.

## What makes this project production Grade?
- Highly Extensible. You can add new calculator functionality without changing existing classes.
- Provides profile specific properties. Dependending the environment(dev, test, prod) the properties will be applied.
- Provides API versioning for backward compatibility
- Provides exception handling that will help for easy debugging if any issue exists during in test or prod environment.

## Features

- Perform basic arithmetic operations: addition, subtraction, multiplication, and division.
- Chain multiple operations in a single calculation.
- CRUD operations for calculations:
    - Create, Read, Update, and Delete calculations.
- RESTful APIs with clear endpoints.

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 and Mockito for testing

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17 or higher**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Apache Maven 3.6.x or higher**: [Download Maven](https://maven.apache.org/download.cgi)
- **Git** (optional, for cloning the repository): [Download Git](https://git-scm.com/downloads)
- **cURL** (optional, for testing the API): [Download cURL](https://curl.se/download.html)
- **Postman** (optional, for testing the API): [Download Postman](https://www.postman.com/downloads/)

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/almasaslamkhan/ebay.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd ebay
   ```

3. **Build the Project with Maven**

   ```bash
   mvn clean install
   ```

   This command compiles the code, runs tests, and packages the application into a JAR file.

---

## Running the Application

You can run the application using Maven or by running the generated JAR file.

### Option 1: Using Maven

```bash
mvn spring-boot:run
```

### Option 2: Running the JAR File

After building the project, run the JAR file located in the `target` directory:

```bash
java -jar target/calculator-1.0.0.jar
```

The application will start on **`http://localhost:9091`**.

---

## API Documentation

### Calculator Endpoints

#### 1. Perform a Calculation

- **Endpoint:** `/v1/calculator/calculate`
- **Method:** `GET`
- **Description:** Performs a basic arithmetic operation between two numbers.
- **Request Body:**
    - `operation` (required): The operation to perform (`ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`).
    - `opearnd1` (required): The first operand (number).
    - `operand2` (required): The second operand (number).
- **Example Request:**

  ```
  GET http://localhost:9091/v1/calculator/calculate
  ```

- **Example Response:**

  ```{
    "code": "200",
    "message": "Success",
    "data": 9.0
	}
  ```

#### 2. Chain Multiple Operations

- **Endpoint:** `/v1/calculator/chain`
- **Method:** `GET`
- **Description:** Performs a sequence of operations starting from an initial value.
- **Request Body:**

  ```json
  {
    "initial_value": 5,
    "operations": [
      {
        "operation": "ADD",
        "operand": 3
      },
      {
        "operation": "MULTIPLY",
        "operand": 2
      }
    ]
  }
  ```
- **Example Request:**

  ```
  http://localhost:9091/v1/calculator/chain
  ```

- **Example Response:**

  ```{
    "code": "200",
    "message": "Success",
    "data": 0.8333333333333334
	}
  ```

---

## Exception Handling

The application includes custom exception handling to provide meaningful error messages.

- **Division by Zero:**

    - **Exception:** `ArithmeticException`
    - **Response:**

      ```json
      {
        "message": "Cannot divide by zero.",
        "details": "Attempted to divide 10.0 by zero."
      }
      ```

- **Calculation Not Found:**

    - **Exception:** `CalculationNotFoundException`
    - **Response:**

      ```json
      {
        "message": "Calculation not found with ID: 99"
      }
      ```

- **Invalid Input Data:**

    - **Exception:** `MethodArgumentNotValidException`
    - **Response:**

      ```json
      {
        "message": "Validation failed",
        "details": "Operand1 cannot be null"
      }
      ```

---

## Screenshots

### Postman Request Example

![Postman Request](Screnshots/POSTMAN_SCREENSHOT_CALCULATE.png)
![Postman Request](Screnshots/POSTMAN_SCREENSHOT_CHAIN.png)



