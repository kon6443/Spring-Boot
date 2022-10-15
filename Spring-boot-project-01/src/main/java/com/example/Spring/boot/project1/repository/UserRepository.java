package com.example.Spring.boot.project1.repository;

import com.example.Spring.boot.project1.docs.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
//    public User findById(String id);
//    public Optional<User> findById(String id);
}
