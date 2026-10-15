package com.firstspring.springbootdemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Show login page                               //  checked
//Google OAuth login                            // checked
//        Logout
//Save new Google user (usually delegated to a service or OAuth success handler) //checked
//Redirect after successful login               //checked
//Handle access denied page                     //sort of checked


@Controller
public class Authcontroller {

        @GetMapping("/login")
        public String login() {
            return "login";
        }

    }










//    @Autowired
//    Userservice userservice;
//    //login page
//    @GetMapping("login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("user", new User());
//        return "login";  // add-question.html
//    }
//
//    // login
//    @PostMapping("/")
//    public String addUser(@ModelAttribute User user) {
//        userservice.addservice(user);
//        return "redirect:/login";
//    }
//    @GetMapping("/register")
//    public String showForm(Model model) {
//        model.addAttribute("registrationForm", new Userservice());
//        return "create-account";
//    }
//
//    @PostMapping("/register")
//    public String submit(@ModelAttribute("registrationForm") Userservice addservice, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "create-account";
//        }
//        // save user...
//        return "redirect:/login";
//    }