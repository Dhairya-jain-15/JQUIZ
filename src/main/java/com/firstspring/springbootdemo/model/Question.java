package com.firstspring.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;
@Data//to avoid getter setter
@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String question_title;
        private String option1;
        private String option2;
        private String option3;
        private String option4;
        private String category;
        private String difficultylevel;
        private String right_answer;
}
