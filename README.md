# Survey System

## Functionalities

- Create Survey
- Get One Survey
- Get All Survey

## Links

> **All parameters are required, so apply it properly**

| No. | Name | Link | Method | Query Parameters | Body Parameters|
|-----|-----|-----|-----|-----|-----|
| 1 | Create Survey | `localhost:8080/survey/` | **POST** | **NONE** | <ul><li>`title`: title of survey</li><li>`description`: description of survey</li><li>`questions`: list of questions <ul><li>`question`: description of question</li></ul></li></ul> |
| 2 | Get One Survey | `localhost:8080/{id}` | **GET** | `id`: integer id of survey | **NONE** |
| 3 | Get All Survey | `localhost:8080/survey/` | **GET** | **NONE** | **NONE** |