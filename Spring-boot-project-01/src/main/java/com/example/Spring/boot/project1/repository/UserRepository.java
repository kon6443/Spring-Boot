package com.example.Spring.boot.project1.repository;

import com.example.Spring.boot.project1.docs.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//public interface UserRepository extends MongoRepository<User, String> {
////    List<User> findByCategory(String id);
//    List<User> findByID(String id);
//    // User findByItemId(String id);
////    User findById(String id);
//}

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);
}
