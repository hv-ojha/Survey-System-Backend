package com.application.SurveySystem.Service;

import com.application.SurveySystem.Model.Question;
import com.application.SurveySystem.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> addQuestion(List<Question> questions) throws Exception {
        if(questions == null) {
            throw new Exception("Parameter Missing");
        }
        return questionRepository.saveAll(questions);
    }
}
