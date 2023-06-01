package com.mamun.ecommerce.controller;

import com.mamun.ecommerce.entity.User;
import com.mamun.ecommerce.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initUserDetails(){
        this.userService.initUserDetails();
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
       User created= this.userService.createUser(user);

       return  ResponseEntity.ok(created);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUser(){
      List<User> users =  this.userService.getUsers();

      return ResponseEntity.ok(users);
    }
}
