package com.application.SurveySystem.RestController;

import com.application.SurveySystem.Model.ResultModel;
import com.application.SurveySystem.Model.Survey;
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
public class SurveyRestController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping("/")
    public ResponseEntity addSurvey(@RequestBody Survey survey) {
        try {
            Survey survey1 = surveyService.addSurvey(survey);
            return correctResponse(survey1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/")
    public ResponseEntity getSurveys() {
        try {
            List<Survey> surveyList = surveyService.getSurveys();
            return correctResponse(surveyList,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getSurvey(@PathVariable Integer id) {
        try {
            Survey survey = surveyService.getSurvey(id);
            return correctResponse(survey,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        } catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping("/response/{id}")
    public ResponseEntity takeSurvey(@PathVariable Integer id, @RequestBody List<ResultModel> resultModels) {
        return correctResponse(resultModels,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
    }

    public ResponseEntity correctResponse(Object value, Object error, int statusCode, String message, HttpStatus status) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("value", value);
        response.put("error", error);
        response.put("status", statusCode);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity errorResponse(Exception ex) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
