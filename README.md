# Project Title

Student Management System

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Custom Code and Classes](#custom-code-and-classes)
- [Validation](#validation)
- [Extensibility](#extensibility)
- [Functionality](#functionality)
- [Default Admin](#default-admin)
- [Default Matric Number](#default-matric-number)
- [Configuration](#configuration)

## Overview

The Student Management System is a Java application designed to manage student information. It provides functionalities to retrieve student details, save new student records, update existing records, and delete student information from a database. The project follows object-oriented programming principles to ensure modularity, extensibility, and maintainability.

## Features

- Retrieve student details by matric number, first name, and last name
- Display all students' information
- Save new student records to the database
- Update existing student records
- Delete student records
- Validation to prevent blank inputs and enforce data format for fields
- Custom `CustomJButton` class to override the button behavior for event notification
- Easy extensibility and modification using abstract classes and modular design

## Installation

1. Clone the repository to your local machine.
2. Set up the database with the necessary tables and schema.
3. Configure the database connection settings in the application.
4. Build the project using a Java IDE or command line tools.

## Usage

1. Launch the application.
2. Use the provided username and password to log in as the default admin.
3. Explore the different functionalities of the application, such as retrieving, saving, updating, and deleting student records.
4. Follow the validation rules for each input field to ensure data integrity.
5. Modify the application as needed by extending the existing classes or adding new ones.

## Custom Code and Classes

The project includes custom code and classes to enhance its functionality and maintainability. Some notable customizations are:

- Grouping similar classes together and using the "Abstract" keyword to indicate abstract classes with potential inner classes.
- Creation of the `CustomJButton` class, which extends the `JButton` class to override the `fireActionPerformed` method for event notification in a "first-to-last" order.

## Validation

The Student Management System implements validation to ensure data integrity and prevent incorrect inputs. The validation includes the following rules:

- Blank inputs are not accepted.
- Numeric fields, such as age, accept only numerical values.
- Text fields, such as first name and last name, allow alphanumeric values.

## Extensibility

The project is designed with extensibility in mind, allowing easy modification and extension of its functionalities. Key aspects of extensibility include:

- Usage of abstract classes to provide a foundation for derived classes and promote code reuse.
- Modular design that separates different components of the application for easy maintenance and modification.
- Implementation of object-oriented programming concepts to encapsulate data and behavior within classes, enabling future enhancements.

## Functionality

The Student Management System provides the following functionalities:

- Retrieval of student details based on matric number, first name, and last name.
- Displaying all student information in a tabular format.
- Saving new student records to the database by providing first name, last name, age, matric number, department, and faculty.
- Updating existing student records with new information.
- Deleting student records from the database.

## Default Admin

The application includes a default admin account with the following credentials:

- Username: admin
- Password: admin#345

You can log in as the default admin to access the system. It is highly recommended to change the default admin password to enhance security.

## Default Matric Number

The project includes a default matric number for testing purposes. The default matric number is "test001". To customize the matric number format, modify the `Validator.matricNumberValidator` method and update the regular expression accordingly. The current regular expression supports the format "XX/XXYYXXX where X represents digits, and Y represents uppercase letters.

## Configuration

The Student Management System can be integrated with any database vendor of your choice. Follow the steps below to configure the database connection:
1. Choose your preferred database vendor (e.g., MySQL, PostgreSQL, Oracle, etc.).
2. Install the appropriate database server and client tools based on the chosen vendor.
3. Create a new database for the Student Management System or use an existing one.
4. Modify the database connection settings in the application code to match your database configuration. Update the URL, username, and password accordingly.
5. Ensure that the necessary database driver library is included in the project's classpath.
6. By following these steps, you can seamlessly connect the Student Management System to your preferred database vendor and leverage its features for data storage and retrieval.
