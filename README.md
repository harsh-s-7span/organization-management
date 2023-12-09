
# Organization Management

The Organization Management project is a Spring Boot application designed to provide a set of APIs for managing organizational data.
<br>
## Versions

**Java:** 11

**Spring Boot:** 2.6.4

**MySQL:** 8


## Configuration

To run the project locally, make sure you have above mentioned version installed. Configure the application.properties file with the appropriate database settings.
```bash
# Database Configuration

spring.datasource.url=jdbc:mysql://localhost:3306/organization_management
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password

```

## Documents

- [SQL File](src/main/resources/sql/script.sql)
- [ER Diagram](src/main/resources/ER-Diagram.png)
- [Postman Collection](src/main/resources/postman_collection.json)

## GitHub Flow
- We have followed GitHub flow so branches created accordingly. I have committed the code in **java-challenge-v2** branch
    - **master -> develop -> java-challenge-v2**

## Additional Notes

- Generally, there are two approaches to work with apis:

    1. Primary Key Exposure:

       We can exposes the primary key of the database for necessary operations. This approach is chosen for simplicity in implementation given the project's size and assignment nature.

    2. UUID-Based Unique Identifier:

       An alternative approach using a UUID-like auto-generated unique identifier for securing operations on data is acknowledged. However, for the scope of this assignment, the first approach is selected as it is more straightforward.
