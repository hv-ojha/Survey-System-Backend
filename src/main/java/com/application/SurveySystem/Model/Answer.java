package com.application.SurveySystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer answerId;
    private boolean answer;

    @ManyToOne
    private Question question;

    @ManyToOne
    @JsonBackReference
    private Response response;
}
