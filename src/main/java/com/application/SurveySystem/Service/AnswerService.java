package com.application.SurveySystem.Service;

import com.application.SurveySystem.Model.Answer;
import com.application.SurveySystem.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    public List<Answer> addAnswers(List<Answer> answers) throws Exception {
        if(answers.isEmpty())
            throw new Exception("No answers received");
        return answerRepository.saveAll(answers);
    }
}
