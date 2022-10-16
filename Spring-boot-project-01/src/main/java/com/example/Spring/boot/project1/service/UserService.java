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
        Optional<User> user = userRepo.findById(id);
        System.out.println("=====getUser=====");
        System.out.println("user id: " + user.get().getId());
        System.out.println("user address: " + user.get().getAddress());
        return user;
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
