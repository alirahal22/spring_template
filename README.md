# Java Spring Boot Template

## Introduction

This is a java spring boot template that can save you a lot of time when starting a new project.
It supports many features.

* ✓ Database Migrations
* ✓ CRUD operations
* ✓ Signature validation
* ✓ Pagination and sorting
* ✓ Filters and Searching
* X Versioning
* X User Authentication
* X Unit and Intergration Testing (Most likely will stick to spring boot's testing module)
* X Media transfer



## Migrations

To run database migrations use the following command:

    mvn clean flyway:migrate

To list the info of the migrations:

    mvn flyway:info
    
Even though it's better to have migrations for the database and just have hibernate validate it, they are not mandatory.
You case use Hibernate's hbm2ddl to have the database generated based on your defined models.
Just open `application.properties` file and replace
    
    spring.jpa.hibernate.ddl-auto=validate
    
with

    spring.jpa.hibernate.ddl-auto=update


## Running the project

To launch the project just run this command from the project's root directory
    
    ./mvnw spring-boot:run


