package com.aryajohary.classroomassignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLogin")
    public String showLogin(){
        return "login-page";
    }

    @GetMapping
    public String showHome(){
        return "home";
    }

}
