package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.schemas.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/syntax")
    public User getSyntax(){
        return new User();
    }
}
