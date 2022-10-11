package com.example.Spring.boot.project1.service;

import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
//    public User getUserById(String id) {
////    public Optional<User> getUserById(String id) {
//        User user = userRepo.findById(id);
//        System.out.println("user id: " + user);
//        System.out.println("user id: " + user.getId());
//        System.out.println("user address: " + user.getAddress());
//        return user;
//    }
}
