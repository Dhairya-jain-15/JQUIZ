package com.firstspring.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String quizname;
    private String category;
    private String description;
    private Integer  time;
    @ManyToMany //quiz with multiple question
    private List<Question> questions;
}
