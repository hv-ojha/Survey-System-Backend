package com.application.SurveySystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer responseId;
    @ManyToOne
    @JsonBackReference
    private Survey survey;
    private String name;
    private String email;
    private String city;
    @OneToMany(mappedBy = "response")
    @JsonManagedReference
    private List<Answer> answers;
}
