package com.spj.spjapp;

import com.spj.spjapp.model.User;
import com.spj.spjapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users") // mila asiana ito ny url satria io no ahalalana ny url an'ilay controller  ex : http://localhost:8080/users/allUsers
public class MessageController {


    @Autowired
    private UserRepository userRepository;
    @GetMapping
    @ResponseBody

    public String showMessage() {
        return "Bonjour, monde !"; // Le message que vous souhaitez afficher dans le navigateur
    }
    @GetMapping("/user")
    @ResponseBody
    public String getUser(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return "Name: " + user.get().getName() + ", Email: " + user.get().getEmail();
        } else {
            return "User not found";
        }
    }
    @GetMapping("/allUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/createUser")
    @ResponseBody
    public ResponseEntity<User>  createUser(@RequestBody User user) {

        //return userRepository.findAll();
        user.setPassword("testpass");
        User savedUser = userRepository.save(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


}