# SANITIZE MICROSERVICE #

SANITIZE Microservice is a Spring Boot-based API service designed for managing and sanitizing sensitive words. The project allows users to add, update, delete, and retrieve sensitive words while offering functionality to sanitize input strings by replacing sensitive words with asterisks.

### Overview
- Framework: Spring Boot 3
- Programming Language: Java 17
- Database: In-memory H2 database
- API Documentation: Swagger UI


### Getting Started

#### Prerequisites

1. Java SDK: Ensure Java SDK 17 is installed.
2.  IDE: Recommended to use IntelliJ IDEA or any IDE supporting Gradle projects.

#### Steps to Set Up
1. ##### Clone the Repository: 
 - git clone <repository-url>. 
 - Navigate to the cloned project directory.

2. ##### Build and Run: 
- Open the project in IntelliJ IDEA.
- Clean and build the project using Gradle.
- Start the application.

   ##### FRONTEND
- Open the Project:
  Launch the project in VS Code or any other IDE that supports JavaScript/HTML.

- Start the Application:
  Use Live Server (or an equivalent tool) to start the application, which will automatically open it in your web browser.

- Ensure Backend is Running:
  Before making any requests from the UI, ensure that the backend server is running.

- Address Potential CORS Errors:
  If you encounter a CORS error, update the @CrossOrigin annotation in the backend controller (SensitiveWordsController) to allow the correct origin.
3. ##### H2 Database Console:
- Access the in-memory H2 database console using the following URL: http://localhost:8080/h2-console.
- Credentials are configured in the application.yml file.

##  API Reference
This project uses Swagger for API documentation. After starting the application, you can access the Swagger UI at: http://localhost:8080/swagger-ui/index.html#/.

### Key Endpoints 
- Add Sensitive Words : Add sensitive words to the database.
- Retrieve All Sensitive Words: Fetch all sensitive words stored in the database.
- Sanitize Strings: Replace sensitive words in an input string with asterisks.
- Update Sensitive Words: Modify an existing sensitive word in the database.
- Delete Sensitive Word: Remove a sensitive word by its ID.

### Test
We use Postman for API testing. The Postman test collection, which includes predefined requests for all relevant endpoints, will be part of the codebase. You can import this collection into Postman to run the tests and interact with the API.

## Additional Notes

- The project is designed to be lightweight and easy to set up, leveraging the H2 in-memory database for quick testing and development.
- Future enhancements could include integration with external databases and deployment configurations.
