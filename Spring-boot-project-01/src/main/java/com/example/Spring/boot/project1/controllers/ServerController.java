package com.example.Spring.boot.project1.controllers;

import com.example.Spring.boot.project1.docs.TokenDto;
import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.docs.UserRoles;
import com.example.Spring.boot.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
//import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//@log4j
@Controller
public class ServerController {

    @RequestMapping(value = "/")
    public String showHome() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + authentication);
        System.out.println("Principal: " + authentication.getPrincipal());
        System.out.println("ID: " + authentication.getName());

        User user = (User) authentication.getPrincipal();
        System.out.println("user id: " + user.getId());
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("user id : " + user.getId());
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
    public String showUser() {return "user";}
    @RequestMapping(value = "/user/:id/:address/:pw/:pwc", method=POST)
    public void signUp(@RequestParam String id, @RequestParam String address, @RequestParam String pw, @RequestParam String pwc) {
        System.out.println("Sign up, id: " + id + ", address: " + address + ", pw: " + pw + ", pwc: " + pwc);
        String errorStatus = userService.userValidateCheck(id, address, pw, pwc);
        if(!errorStatus.isBlank()) {
            // error here.
            System.out.println(errorStatus);
        }
        System.out.println("Original password: " + pw);
        pw = JasyptConfig.encryptPassword(pw);
        System.out.println("Encrypted password: " + pw);
        userService.registerUser(id, address, pw);
    }

    // @ResponseBody: URL ????????? ?????? ???????????? ????????? ??????, ????????? ?????? ???????????? ????????? ?????? ????????? ?????????.
    // If @ResponseBody exists above the API, the API will return String object.
    // If @ResponseBody does not exist above the API, the API will return a file with the same name as the String value.
    @ResponseBody
    @RequestMapping(value = "/user/signin", method=POST)
    // ResponseEntity
    public ResponseEntity<TokenDto> signIn(@RequestParam String id, @RequestParam String pw, HttpServletResponse response) {
        String token = userService.logIn(id, pw);
        response.setHeader("Authorization", token);
//        return token;
        HttpHeaders httpHeaders = new HttpHeaders();
//        return new ResponseEntity<>(new TokenDto(token), httpHeaders, HttpStatus.OK);
        return ResponseEntity.ok(new TokenDto(token));
    }
    @RequestMapping(value = {"/chat"}, method=GET)
    public String showChat(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println("Name: " + user.get_id() + ", Address: " + user.getAddress());
        return "chat";
    }

}