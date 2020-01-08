package com.application.SurveySystem.Repository;

import com.application.SurveySystem.Model.Response;
import com.application.SurveySystem.Model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    public List<Response> findBySurvey(Survey survey);
}
