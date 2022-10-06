package com.example.Spring.boot.project1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

//@log4j
@Controller
public class ServerController {
//    @RequestMapping(value = "/", method=GET)
//    @RequestMapping(value = {"/", "/home.html"}, method=GET)
    @ResponseBody
    @GetMapping(value = {"/", "/home.html"})
    public String showHome() {
        System.out.println("showHome function has been called.");
        return "home";
    }

//    @ResponseBody
//    @RequestMapping(value = {"/user", "/user.html"}, method=GET)
//    public String showUser() {
//        System.out.println("showUser.");
//        return "user";
//    }
//    @GetMapping("/chat")
//    public ResponseEntity<String> showChat() {
//        System.out.println("showChat.");
//        return ResponseEntity.ok("Chat page!");
//    }
}