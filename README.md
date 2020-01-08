# Survey System

Basic survey system which creates survey and takes response

## Technologies Used

- Java
- Spring Boot
- H2 Database(In-memory database service provided by spring framework for testing)
- PostgreSQL
- Postman (for API Testing)

## Functionality

- Create Survey
- Get One Survey / Result of Survey
- Get All Survey
- Take Survey
- Get Single Response
- Get result of survey

## Links

> **All parameters are required, so apply it properly**

| No. | Name | Link | Method | Query Parameters | Body Parameters|
|-----|-----|-----|-----|-----|-----|
| 1 | Create Survey | `localhost:8080/survey/` | **POST** | **NONE** | <ul><li>`title`: title of survey</li><li>`description`: description of survey</li><li>`questions`: list of questions <ul><li>`question`: description of question</li></ul></li></ul> |
| 2 | Get One Survey | `localhost:8080/{id}` | **GET** | `id`: integer id of survey | **NONE** |
| 3 | Get All Survey | `localhost:8080/survey/` | **GET** | **NONE** | **NONE** |
| 4 | Take a Survey | `localhost:8080/survey/response/` | **POST** | **NONE** | <ul><li>`name` : Name of responder(String)</li><li>`email` : Email of responder(String)</li><li>`survey` : Survey Object with parameter `surveyId` of valid survey</li><li>Array of answers <ul><li>`question`: Object of Question with parameter `questionId` of valid question</li><li>`answer`: boolean response of the question</li></ul></li></ul> |
| 4 | Single Response | `localhost:8080/survey/response/{id}` | **GET** | `id` : integer id of response | **NONE** |
| 4 | Result of Survey | `localhost:8080/survey/result/{id}` | **GET** | `id` : integer id of survey | **NONE** |

## Examples

1. Create Survey = `localhost:8080/survey/`,  TYPE = **POST**
    1. Body =  `{
     	"title" : "First Survey",
     	"description" : "This is my First survey",
     	"question" : [
     		{
     			"question" : "this is First first question"
     		},
     		{
     			"question" : "this is First second question"
     		},
     		{
     			"question" : "this is First third question"
     		}
     	]
     }`
     

2. Take a Survey = `localhost:8080/survey/response/`, TYPE = **POST**
    1. Body =  `{
     	"name" : "Harshvardhan",
     	"email" : "ojhaharsh7@gmail.com",
     	"city" : "surat",
     	"survey" : {
     		"surveyId" : 1
     	},
     	"answers" : [
     		{
     			"question" : {
     				"questionId" : 2
     			},
     			"answer" : false
     		},
     		{
     			"question" :  {
     				"questionId" : 3
     			},
     			"answer" : true
     		}
     	]
     }`