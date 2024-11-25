package com.pharvinex.pharvinexGroup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public List<User> getUser(){
        return userService.getUser();
    }

    @GetMapping("/admin/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }




    @PostMapping("/public/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }


}