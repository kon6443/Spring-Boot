package com.example.Spring.boot.project1.controllers;

import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//@log4j
@Controller
public class ServerController {

    @RequestMapping(value = "/")
    public String showHome() {
        return "home";
    }
    @RequestMapping(value = {"/tetris"}, method=GET)
    public String playTetris() {
        return "/Tetris/tetris";
    }
//    @RequestMapping(value = {"/board"}, method=GET)
//    public String showBoard() {
//        return "/Board/board";
//    }

    @Autowired
    UserService userService;
    @RequestMapping(value = {"/user"}, method=GET)
    public String showUser() {
        return "user";
    }
    @RequestMapping(value = "/user/:id/:pw", method=POST)
    public void signIn(@RequestParam String id, @RequestParam String pw) {
        System.out.println("Typed user id: " + id + ", typed user pw: " + pw);
//        List<User> users = userService.getEveryone();
        Optional<User> user = userService.getUser(id);
//        return user;
    }
    @RequestMapping(value = {"/chat"}, method=GET)
    public String showChat() {
        return "chat";
    }

}