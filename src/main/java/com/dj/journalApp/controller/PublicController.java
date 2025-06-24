package com.dj.journalApp.controller;

import com.dj.journalApp.entity.User;
import com.dj.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "JournalApp is OK!";
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return user;
    }
}
