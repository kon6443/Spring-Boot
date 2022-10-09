package com.example.Spring.boot.project1.controllers;

import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    UserService userService;
    @RequestMapping(value = {"/user"}, method=GET)
    public String showUser() {
        System.out.print(userService.getAllItems());
        return "user";
    }
//    @RequestMapping(value = "", method=GET)
//    public List<User> getAllUsers() {
//        LOG.info("Getting all users.");
//        return userRepository.findAll();
//    }
    @RequestMapping(value = {"/chat"}, method=GET)
    public String showChat() {
        return "chat";
    }

}