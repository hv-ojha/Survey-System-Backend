# Survey System

Basic survey system which creates survey and takes response

## Technologies Used

- Java
- Spring Boot
- H2 Database(In-memory database service provided by spring framework)

## Functionality

- Create Survey
- Get One Survey / Result of Survey
- Get All Survey
- Take Survey

## Links

> **All parameters are required, so apply it properly**

| No. | Name | Link | Method | Query Parameters | Body Parameters|
|-----|-----|-----|-----|-----|-----|
| 1 | Create Survey | `localhost:8080/survey/` | **POST** | **NONE** | <ul><li>`title`: title of survey</li><li>`description`: description of survey</li><li>`questions`: list of questions <ul><li>`question`: description of question</li></ul></li></ul> |
| 2 | Get One Survey / Result of Survey | `localhost:8080/{id}` | **GET** | `id`: integer id of survey | **NONE** |
| 3 | Get All Survey | `localhost:8080/survey/` | **GET** | **NONE** | **NONE** |
| 4 | Take a Survey | `localhost:8080/survey/response/{id}` | **PUT** | `id` : integer id of survey | <ul><li>Array of result <ul><li>`id`: integer id of Question</li><li>`answer`: boolean response of the question</li></ul></li></ul> |