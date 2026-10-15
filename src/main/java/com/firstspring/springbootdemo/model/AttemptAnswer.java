package com.firstspring.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class AttemptAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String selectedAnswer;

    private boolean correct;
    @ManyToOne
    private QuizAttempt attempt;

    @ManyToOne
    private Question question;
}
