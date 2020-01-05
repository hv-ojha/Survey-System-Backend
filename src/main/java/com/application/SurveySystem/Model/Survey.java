package com.application.SurveySystem.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer surveyId;
    private String title;
    private String description;
    @OneToMany(mappedBy = "survey")
    @JsonManagedReference
    private List<Question> question;
    private Integer noOfTotalResponse;
}
