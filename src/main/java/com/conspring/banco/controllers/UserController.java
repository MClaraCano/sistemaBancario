package com.conspring.banco.controllers;

import com.conspring.banco.mappers.UserMapper;
import com.conspring.banco.models.User;
import com.conspring.banco.models.dtos.UserDto;
import com.conspring.banco.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    public UserController() {
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> usuarios = userService.getUsers();
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }



    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(201).body(user);
    }

}
