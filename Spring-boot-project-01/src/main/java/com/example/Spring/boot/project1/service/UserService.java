package com.example.Spring.boot.project1.service;

import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public Optional<User> getUser(String id) {
//        User user = userRepo.findById(id).get();
        Optional<User> user = userRepo.findByid(id);
        System.out.println("=====getUser=====");
        System.out.println("user id: " + user.get().getId());
        System.out.println("user address: " + user.get().getAddress());
        return user;
    }
    public String userValidateCheck(String id, String address, String pw, String pwc) {
        if(id.isBlank()) return "Please type your ID.";
        Optional<User> user = userRepo.findByid(id);
        if(user.isPresent()) return "User name: " + user.get().getId() + " already exists";
        if(address.isBlank()) return "Please type your address.";
        if(pw.isBlank()) return "Please type your password.";
        if(pwc.isBlank()) return "Please type your password confirmation.";
        if(!pw.equals(pwc)) return "Your password and password confirmation is not matched!";
        return "";
    }
    public String registerUser(String id, String address, String pw) {
        User user = new User();
        user.setId(id);
        user.setAddress(address);
        user.setPw(pw);
        userRepo.save(user);
        return "Your account has been created successfully, you can now log in.";
    }
//    public User searchByAddress(String address) {
//        User user = userRepo.findByAddress(address);
//        System.out.println("=====searchByAddress=====");
//        System.out.println("ID: " + user.getId());
//        System.out.println("Address: " + user.getAddress());
//        System.out.println();
//        return user;
//    }
    public List<User> getEveryone() {
        List<User> users = userRepo.findAll();
        for(User user : users) {
            System.out.println("_ID: " + user.get_id());
            System.out.println("ID: " + user.getId());
            System.out.println("Address: " + user.getAddress());
            System.out.println();
        }
        return users;
    }
}
