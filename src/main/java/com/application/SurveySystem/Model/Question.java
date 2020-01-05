package com.application.SurveySystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;
    @ManyToOne
    @JsonBackReference
    private Survey survey;

    private String question;
    private Integer numberOfYes;
    private Integer numberOfNo;
    private Integer numberOfResponses;
}
