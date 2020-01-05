package com.application.SurveySystem.Service;

import com.application.SurveySystem.Model.Question;
import com.application.SurveySystem.Model.ResultModel;
import com.application.SurveySystem.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Question getQuestion(Integer id) throws Exception {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(!optionalQuestion.isPresent())
            throw new Exception("Question with number " + id +  " not found");
        return optionalQuestion.get();
    }

    public List<Question> incrementResponse(Integer surveyId, List<ResultModel> resultModels) throws Exception {
        List<Question> questionList = new ArrayList<>();
        for(ResultModel resultModel : resultModels) {
            try {
                Question question = getQuestion(resultModel.getId());
                if(surveyId == question.getSurvey().getSurveyId()) {
                    synchronized (question) {
                        question.setNumberOfResponses(question.getNumberOfResponses() == null ? 1 : question.getNumberOfResponses() + 1);
                        if (resultModel.isAnswer()) {
                            question.setNumberOfYes(question.getNumberOfYes() == null ? 1 : question.getNumberOfYes() + 1);
                        } else {
                            question.setNumberOfNo(question.getNumberOfNo() == null ? 1 : question.getNumberOfNo() + 1);
                        }
                        questionList.add(question);
                    }
                }
            } catch(Exception ex) {
                continue;
            }
        }
        if(questionList.isEmpty())
            throw new Exception("Questions with following ids not found");
        return addQuestion(questionList);
    }
}
