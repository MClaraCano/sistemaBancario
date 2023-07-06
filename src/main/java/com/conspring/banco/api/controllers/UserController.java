package com.conspring.banco.api.controllers;

import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.domain.models.User;
import com.conspring.banco.api.dtos.UserDto;
import com.conspring.banco.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    public UserController() {
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsersDto(){
        List<UserDto> usuariosDto = userService.getUsers();
        return ResponseEntity.status(200).body(usuariosDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable Long id){
        UserDto UserDto = userService.getUserById(id);
        return ResponseEntity.ok().body(UserDto);
    }



    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        userDto = userService.createUser(userDto);
        return ResponseEntity.status(201).body(userDto);
    }

    @PutMapping("/usermod")
    public ResponseEntity<UserDto> modificarUser(@RequestBody UserDto userDto) throws NoSeEncontroE {
        userDto = userService.modificarUser(userDto);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/userdelete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.borrarById(id);
        return ResponseEntity.ok().body("Usuario eliminado correctamente");
    }

}
