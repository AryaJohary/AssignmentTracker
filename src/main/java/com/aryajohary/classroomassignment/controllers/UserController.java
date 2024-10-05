package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.repos.UserRepo;
import com.aryajohary.classroomassignment.schemas.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/syntax")
    public User getSyntax(){
        return new User();
    }
    @GetMapping("/showAll")
    public String findAllUsers(Model theModel){
        theModel.addAttribute("users", userRepo.findAll());
        return "usersList";
    }
}
