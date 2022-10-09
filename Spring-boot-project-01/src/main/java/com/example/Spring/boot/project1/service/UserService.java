package com.example.Spring.boot.project1.service;

import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public List<User> getAllItems(){
        return userRepo.findAll();
    }
//    public User getItemById(String id){
//        // User user = userRepo.findById(id);
//        User user = userRepo.findById(id);
//        return item;
//    }
}
