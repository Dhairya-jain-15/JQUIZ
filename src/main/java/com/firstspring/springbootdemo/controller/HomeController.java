package com.firstspring.springbootdemo.controller;
//        Home page         //checked
//        Dashboard
//        Explore quizzes
//        My quizzes page       //checked
//        My attempts page
//        Profile page          //checked
//        About page
//        Contact page

import com.firstspring.springbootdemo.model.Quiz;
import com.firstspring.springbootdemo.service.Quizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quizapp")
public class HomeController {
    //home page
    @GetMapping
    public String home(Model model) {
        //frontend ke maange hue attributes (Model ek predefined object used to hold information coming from http)
        model.addAttribute("pageTitle", "JQuiz - Online Quiz Maker");
        model.addAttribute("brandName", "JQuiz");

        model.addAttribute("heroHighlight", "quiz maker");

        model.addAttribute("heroSubtitle",
                "Make quizzes with multiple question types in minutes.");

        model.addAttribute("ctaLabel", "Create a Quiz");

        model.addAttribute("quizMakerLinkText",
                "online quiz maker");

        model.addAttribute("infoParagraph",
                "Quickly and easily create interactive online quizzes for free!");

//       display quiz card
        List<Quiz> quizzes = quizservice.getAllQuizzes();

        model.addAttribute("quizzes", quizzes);

        return "quizapp";
    }

    //developer page
    @GetMapping("Quizdeveloper")
    public String show() {
        return "Quizdeveloper";
    }


    //creation page
    @GetMapping("create-quiz")
    public String createquiz(Model model) {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("QuizName", "");
        model.addAttribute("category", "");
        model.addAttribute("Description", "");
        model.addAttribute("Time", "");

        System.out.println("creation");
        return "create-quiz";
    }

    @Autowired
    Quizservice quizservice;
    /*
    quiz ke saare attributes exept question ke save hojaege or\
     id k basis pe aage k list of question dusre page se aajenge
    */

    // Save quiz
    @PostMapping("/create-quiz")
    public String addquiz(@ModelAttribute Quiz quiz) {
        System.out.println("working");
        Quiz savedquiz = quizservice.addservice(quiz);
        int idofquiz = savedquiz.getId();
        return "redirect:/quizapp/" + idofquiz + "/select-question";
    }


}

