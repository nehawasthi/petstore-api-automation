
# Petstore API Automation Framework

Welcome to the Petstore API Automation repository!  
This project is built using Java and RestAssured to test the core functionalities of the Swagger Petstore API. It includes positive and negative test cases for all key operations, such as creating, updating, retrieving, and deleting user data. The framework is designed for easy integration, scalability, and clean reporting.

---

## Project Highlights

- Built using Java 11+, Maven, TestNG, and RestAssured
- Organized test structure with reusable utilities and base classes
- Covers both functional and negative API test scenarios
- Dynamic test data loaded from external JSON files
- Rich test reports using ExtentReports
- Retry mechanism for flaky test scenarios
- Integrated with Playwright (for optional UI Swagger validation)
- Ready for CI/CD with GitHub Actions

---

## Tech Stack

| Tool/Library      | Purpose                          |
|-------------------|----------------------------------|
| Java              | Programming Language             |
| RestAssured       | API Testing Library              |
| TestNG            | Test Management Framework        |
| Maven             | Build & Dependency Management    |
| ExtentReports     | Reporting                        |
| Log4j             | Logging                          |
| Playwright (UI)   | Optional UI Testing              |
| GitHub Actions    | CI/CD Pipeline (Optional)        |

---

## Folder Structure

petstore-api-automation/
├── apis/                   # API test classes
│   ├── CreateUserTest.java
│   ├── GetUserByNameTest.java
│   ├── UpdateUserTest.java
│   └── DeleteUserTest.java
├── base/                   # Base classes & configurations
│   ├── BaseTest.java
│   ├── RetryAnalyzer.java
│   └── TestListener.java
├── reports/                # ExtentReport configuration
│   └── ExtentManager.java
├── ui/                     # Swagger UI test class
│   └── SwaggerUITest.java
├── utils/                  # Utility classes
│   ├── JsonUtils.java
│   └── LogUtils.java
├── resources/
│   ├── testdata.json       # Test data for user scenarios
│   └── log4j.properties    # Logger configuration
├── testng.xml              # TestNG suite configuration
├── pom.xml                 # Maven dependency file
└── README.md               # Project documentation

---

## Setup Instructions

### 1. Clone the Repository

git clone https://github.com/nehawasthi/petstore-api-automation.git
cd petstore-api-automation

### 2. Build the Project

Make sure you have Maven and Java installed:

mvn clean install

---

## How to Run Tests

### 1. Run All Tests via TestNG

mvn test -DsuiteXmlFile=testng.xml

Or simply right-click on testng.xml in your IDE and choose "Run as TestNG Suite".

### 2. Run Individual Test Class

You can run a specific test directly in your IDE, such as:

- CreateUserTest.java
- UpdateUserTest.java

---

## Test Coverage Summary

### Positive Scenarios

- Create a valid user
- Retrieve user by username
- Update user information
- Delete an order

### Negative Scenarios

- Fetch a non-existent user
- Create a user with invalid fields
- Update user with empty payload
- Delete invalid order

---

## Test Data Source

Test data is dynamically read from:

src/test/resources/testdata.json

Example structure:

{
  "user": {
    "id": 1234,
    "username": "Neha_Awasthi",
    "email": "neha@example.com",
    "password": "test123"
  },
  "invalidUser": {
    "id": "invalid",
    "username": "",
    "email": "not-an-email"
  }
}

---

## Reports & Logs

After test execution:

- Extent Report: Open test-output/ExtentReports/index.html
- Logs: Printed in console or file based on log4j.properties

---

## Retry Logic

All failed tests are retried automatically using RetryAnalyzer.java.  
It improves test reliability in case of temporary failures or timeouts.

---

## Optional Swagger UI Test

Includes a basic Playwright test to validate Swagger UI page loading and user section expansion.

page.navigate("https://petstore.swagger.io/");
page.locator("#operations-user-createUser button").click();

---

## GitHub Actions (CI/CD)

Optionally configure .github/workflows/ci.yml to run the tests on each commit or PR.

---

## Contribution

Want to improve something? You're welcome!

Steps to contribute:
1. Fork the repository
2. Create a new branch
3. Make your changes
4. Push the branch and raise a pull request

---

## License

This project is licensed under the MIT License.

---

## Maintainer

Neha Awasthi  
GitHub: https://github.com/nehawasthi
