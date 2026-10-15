package com.firstspring.springbootdemo.service;

import com.firstspring.springbootdemo.dao.Userdao;
import com.firstspring.springbootdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Userservice {
    @Autowired
    Userdao userdao;

    public String addservice(User user) {
        userdao.save(user);
        return "saved";
    }
}

