package com.pharvinex.pharvinexGroup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/users")
    public String getUser(){
        System.out.println("Getting user");
        return "Users";
    }

    @GetMapping("/current-user")
    public String getLoggedUser(Principal principal){
        return principal.getName();
    }
}