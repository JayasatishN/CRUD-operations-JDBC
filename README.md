# JDBC CRUD Operations

This project demonstrates the implementation of basic CRUD (Create, Read, Update, Delete) operations in Java using JDBC and a MySQL database.

## Features

- **Create**: Adds a new  record to the `Registration` table.
- **Read**: Retrieve all registration details records from the database.
- **Update**: Modify existing Registration details.
- **Delete**: Remove Registration records by ID.

## Prerequisites

- Java Development Kit (JDK) installed.
- MySQL Database installed and running.
- MySQL JDBC Driver (`mysql-connector-java`) included in the project dependencies.
- A MySQL database with a table named `employees`:
  
  ```sql
  CREATE TABLE Registration (
    id SERIAL PRIMARY KEY,                   
    name VARCHAR(100) NOT NULL,               
    email VARCHAR(255) NOT NULL UNIQUE,       
    date_of_birth DATE NOT NULL,
    phone_number VARCHAR(15);
);
