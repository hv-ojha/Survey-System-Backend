package com.application.SurveySystem.RestController;

import com.application.SurveySystem.Model.*;
import com.application.SurveySystem.Service.AnswerService;
import com.application.SurveySystem.Service.QuestionService;
import com.application.SurveySystem.Service.ResponseService;
import com.application.SurveySystem.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private AnswerService answerService;

    // ADD SURVEY
    @PostMapping("/")
    public ResponseEntity addSurvey(@RequestBody Survey survey) {
        try {
            // Service to add service
            Survey survey1 = surveyService.addSurvey(survey);
            return correctResponse(survey1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch(Exception ex) {
            // IF ANY ERROR OCCUR
            return errorResponse(ex);
        }
    }

    // GET ALL SURVEYS
    @GetMapping("/")
    public ResponseEntity getSurveys() {
        try {
            //Service call to get all surveys
            List<Survey> surveyList = surveyService.getSurveys();
            return correctResponse(surveyList,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    // GET SINGLE SURVEY USING ID
    @GetMapping("/{id}")
    public ResponseEntity getSurvey(@PathVariable Integer id) {
        try {
            // Service call to get single survey using integer id
            Survey survey = surveyService.getSurvey(id);
            return correctResponse(survey,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    //Get single response
    @GetMapping("/response/{id}")
    public ResponseEntity getResponse(@PathVariable Integer id) {
        try {
            Response response = responseService.getResponseById(id);
            return correctResponse(response,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    // RESULT OF SURVEY
    @GetMapping("/result/{id}")
    public ResponseEntity getResult(@PathVariable Integer id) {
        try {
            Survey survey = surveyService.getSurvey(id);
            HashMap<Object, Object> result = new HashMap<>();
            result.put("Id",survey.getSurveyId());
            result.put("Title", survey.getTitle());
            result.put("Description", survey.getDescription());
            result.put("Total_Responses", survey.getNoOfTotalResponse());
            HashMap questions = new HashMap();
            List<Question> questionList = survey.getQuestion();
            for(Question question : questionList) {
                questions.put("Question" , question.getQuestion());
                questions.put("Total_Response", question.getNumberOfResponses());
                questions.put("Percent_of_Yes", (question.getNumberOfYes()*100/question.getNumberOfResponses()));
                questions.put("Percent_of_No", (question.getNumberOfNo()*100/question.getNumberOfResponses()));
            }
            result.put("Questions",questions);
            return correctResponse(result,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse(e);
        }
    }

    // POST RESPONSE IN THE DATABASE
    @PostMapping("/response/")
    public ResponseEntity takeSurvey(@RequestBody Response response) {
        try {
            // Increase count in survey
            Survey survey = surveyService.submitResponse(response);
            // Submit response to the response table
            Response response1 = responseService.addResponse(response);
            // Get all answers
            List<Answer> list = response.getAnswers();
            for (Answer answer : list) {
                //Set response for each answer
                answer.setResponse(response);
                // Set question for each answer
                answer.setQuestion(questionService.getQuestion(answer.getQuestion().getQuestionId()));
            }
            // Submit answers in answers table
            List<Answer> answers = answerService.addAnswers(list);
            // increment response in questions
            List<Question> questionList = questionService.incrementResponse(response1.getSurvey().getSurveyId(),response);
            return correctResponse(response1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    //Function to return correct response
    public ResponseEntity correctResponse(Object value, Object error, int statusCode, String message, HttpStatus status) {
        HashMap<Object, Object> response = new HashMap<>();
        // value to be passed in correct response
        response.put("value", value);
        // error received
        response.put("error", error);
        // status code received
        response.put("status", statusCode);
        // message to be display
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity errorResponse(Exception ex) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("error", HttpStatus.BAD_REQUEST);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
