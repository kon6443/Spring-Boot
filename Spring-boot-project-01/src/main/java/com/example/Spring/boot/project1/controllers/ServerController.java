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
    @RequestMapping(value = "/user/signin", method=POST)
    public void signIn(@RequestParam String id, @RequestParam String pw) {
        String temp = userService.logIn(id, pw);
    }
    @RequestMapping(value = "/user/signin", method=POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }
    @RequestMapping(value = {"/chat"}, method=GET)
    public String showChat() {
        return "chat";
    }

}