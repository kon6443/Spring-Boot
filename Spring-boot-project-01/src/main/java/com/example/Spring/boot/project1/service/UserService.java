package com.example.Spring.boot.project1.service;

import com.example.Spring.boot.project1.controllers.JasyptConfig;
import com.example.Spring.boot.project1.docs.User;
import com.example.Spring.boot.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.Spring.boot.project1.controllers.JasyptConfig.decryptPassword;

@Service
public class UserService {
    /**
     * Injectioning an object to call registered bean by using @Autowired annotation, (Auto injection)
     * Controller has a dependency on Service interface, and Controller and Service are on a dependency relationship.
     *
     * @Autowired 어노테이션을 사용해 등록된 빈을 불러와 객체를 주입시킴 (자동 의존성 주입).
     * Controller는 Service 인터페이스에 의존성을 가지고 있으며, Controller와 Service인터페이스는 의존관계에 있다.
     */
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
    public String logIn(String id, String pw) {
        System.out.println("Typed user id: " + id + ", typed user pw: " + pw);
        Optional<User> user = userRepo.findByid(id);
        if(user.isEmpty()) return "There is no user named: " + id;
        String decryptedPw = decryptPassword(user.get().getPw());
        return "";
    }
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
