package com.mamun.ecommerce.controller;

import com.mamun.ecommerce.entity.User;
import com.mamun.ecommerce.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@RequestMapping("/user")
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

//    @GetMapping("/adminUser")
//    public ResponseEntity<String> adminUser(){
//
//        return ResponseEntity.ok("This url is for admin user");
//    }
    @GetMapping("/normalUser")
    public ResponseEntity<String> normalUser(){



        return ResponseEntity.ok("This url is for normal user");
    }

}
