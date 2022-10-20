package com.example.Spring.boot.project1.controllers;

import com.example.Spring.boot.project1.docs.User;

import java.util.Optional;

public class userValidateCheck {
    public String userValidateCheck(String id, String address, String pw, String pwc) {
        if(id=="") return "Please type your ID.";
        Optional<User> user = userService.getUser(id);
        if(user) return "User name: " + user.id + "already exists";
        if(address=="") return "Please type your address.";
        if(pw=="") return "Please type your password.";
        if(pwc=="") return "Please type your password confirmation.";
        if(pw != pwc) return "Your password and password confirmation is not matched!";
    }
}

