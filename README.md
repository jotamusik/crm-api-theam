# CRM - API

## Description
This is a CRM Rest-API created with SpringBoot and an H2 database that turns on at the same moment as the backend.

## Things to know
### Utils
In this project, I'm using:
 - Hibernate for accessing to database
 - io.jwt for building the access tokens
 - Lombok to make the code more cleaner by adding annotations to create the getter/setter
 - Maven to manage dependencies and provide an easy way to run the project

### Structure
The application is structured in layers:
1. __Controllers__: Entry points for each API endpoint. It's function is to call a service. It don't manage any business logic
2. __Services__: They contain the business logic. 
3. __Repositories__: All the possible model's operations
4. __Models__: Persistence layer. Represent the data that will be storage

## How to run
1. Be sure to have Java 12 and Maven3 installed
2. Install the project's dependencies: `mvn install`
3. Run the project: `mvn spring-boot:run`

