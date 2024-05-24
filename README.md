# Employee Database Management System

## Overview
The Employee Database Management System project provides functionalities to manage employees within an organization. It allows users to add, update, delete, and retrieve information about employees. Additionally, it includes features like sending email notifications to managers and retrieving manager details based on hierarchy levels. The project is implemented using Java Spring Boot and Hibernate.

## Features
- **Add Employee:** Users can add a new employee with details such as name, phone number, email, reports to (manager), and profile image URL.
- **Update Employee:** Users can update various attributes of an employee, including name, phone number, email, reports to, and profile image.
- **Delete Employee:** Users can delete an employee from the database.
- **Retrieve Employees:** Users can retrieve a paginated list of all employees sorted by specified fields.
- **Send Email Notifications:** The system sends email notifications to the level 1 manager when a new employee is added.
- **Retrieve Manager Details:** Users can retrieve the name of the manager at the nth level above a given employee.

## Tech Stack
- **Java:** The core programming language used for backend development.
- **Spring Boot:** The framework used for building RESTful APIs and handling dependencies.
- **Hibernate:** The ORM framework used for mapping Java objects to database tables.
- **MySQL:** The relational database management system used for storing employee data.

## Project Structure
- **Model:** Defines the Employee entity with attributes such as ID, name, phone number, email, reports to, and profile image.
- **Service:** Implements business logic for employee management, including adding, updating, deleting, and retrieving employees.
- **Controller:** Contains RESTful API endpoints for handling HTTP requests related to employee operations.
- **Repository:** Provides an interface for performing CRUD operations on the database.

## Setup Instructions
1. Clone the repository to your local machine.
2. Set up a MySQL database and configure the database connection properties in the `application.properties` file.
4. Build and run the project using Maven or your preferred IDE.
5. Access the RESTful APIs using tools like Swagger.

## API Documentation
- **POST /employee:** Add a new employee.
  - Intitially we will be having no managers, to refer to so we leave the reportsTo field empty
  ![Screenshot (913)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/ac1f5716-7c35-48ed-a790-b616f9318a00)
  - After adding an employee, we can get the id of that employee and we can use that id to fill the refersTo field
  ![Screenshot (915)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/d3081ab8-196a-4fa6-b516-26835294f6bb)

- **GET /employees:** Retrieve a paginated list of all employees.
  - Input to get all the employees and we can do pagination also like into how many pages we want to divide the list and how many entries we should get on each page and we can do sorting on different fields like employeeName.
    ![Screenshot (916)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/f968df34-68de-49c1-b44c-407b5aa71a8b)

- **PUT /byId:** Update an existing employee by ID.
  - We can update the employee details like name, emailId, phoneNumber, reportsTo and profileImage using this api and also we can only update one or multiple fields according to our requirement and it is not mandatory to update all the fields
    ![Screenshot (919)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/aadf7c3a-444c-49cd-8bf6-ec58316b54d0)  

- **DELETE /byId:** Delete an employee by ID.
  - we can delete the employee from the database using the employeeId
    ![Screenshot (921)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/6e958118-77e8-4112-95ff-bf9961482873)

- **GET /nThLevelManager:** Retrieve the name of the manager at the nth level above a given employee.
  ![Screenshot (917)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/d79bbe07-8687-4b9c-bab1-72a7a5bbafe8)

## Contributors
- Manikanta Kotekal Methukula

