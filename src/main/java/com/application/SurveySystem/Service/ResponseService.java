package com.application.SurveySystem.Service;

import com.application.SurveySystem.Model.Response;
import com.application.SurveySystem.Model.Survey;
import com.application.SurveySystem.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;

    public Response addResponse(Response response) throws Exception {
        if(response == null)
            throw new Exception("No response received");
        if(response.getAnswers().isEmpty())
            throw new Exception("Please enter answers");
        else if(response.getSurvey() == null)
            throw new Exception("No survey received");
        return responseRepository.save(response);
    }
    public List<Response> getAllResponseBySurvey(Survey survey) throws Exception {
        if(survey == null)
            throw new Exception("No survey found");
        return responseRepository.findBySurvey(survey);
    }
    public Response getResponseById(Integer id) throws Exception {
        Optional<Response> response = responseRepository.findById(id);
        if(!response.isPresent())
            throw new Exception("No response found");
        return response.get();
    }
}
