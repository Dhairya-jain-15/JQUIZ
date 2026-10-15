package com.firstspring.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer score;

    private Integer totalQuestions;

    private String status;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private User user;
}