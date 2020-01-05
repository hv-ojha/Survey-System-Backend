package com.application.SurveySystem.Service;

import com.application.SurveySystem.Model.Question;
import com.application.SurveySystem.Model.ResultModel;
import com.application.SurveySystem.Model.Survey;
import com.application.SurveySystem.Repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionService questionService;

    public Survey addSurvey(Survey survey) throws Exception {
        if(survey == null) {
            throw new Exception("Parameter Missing");
        }
        Survey survey1 = surveyRepository.save(survey);
        questionService.addQuestion(survey.getQuestion());
        return survey1;
    }

    public Survey submitResponse(Survey survey, List<ResultModel> resultModels) throws Exception {
        if(survey == null && resultModels.isEmpty()) {
            throw new Exception("No such Survey Exist");
        }
        synchronized (survey) {
            survey.setNoOfTotalResponse(survey.getNoOfTotalResponse() == null ? 1 : survey.getNoOfTotalResponse() + 1);
            questionService.incrementResponse(survey.getSurveyId(), resultModels);
        }
        return surveyRepository.save(survey);
    }

    public List<Survey> getSurveys() throws Exception {
        List<Survey> surveyList = surveyRepository.findAll();
        if(surveyList.isEmpty()) {
            throw new Exception("No Survey found");
        }
        return surveyList;
    }

    public Survey getSurvey(Integer id) throws Exception {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(!optionalSurvey.isPresent()) {
            throw new Exception("No such Survey Exist");
        }
        return optionalSurvey.get();
    }
}
