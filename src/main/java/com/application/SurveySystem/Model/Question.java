package com.application.SurveySystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    private String question;
    private Integer numberOfYes;
    private Integer numberOfNo;
    private Integer numberOfResponses;
}
