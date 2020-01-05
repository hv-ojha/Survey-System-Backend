package com.application.SurveySystem.Service;

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

    public Survey addSurvey(Survey survey) throws Exception {
        if(survey == null) {
            throw new Exception("Parameter Missing");
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
