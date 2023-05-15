package com.conspring.banco.services;

import com.conspring.banco.mappers.UserMapper;
import com.conspring.banco.models.User;
import com.conspring.banco.models.dtos.UserDto;
import com.conspring.banco.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<UserDto> getUsers(){
            List<User> usuariosUser = userRepository.findAll();
            List<UserDto> usuariosUDto = usuariosUser.stream()
                    .map(userMapper::UserToDtoMap)
                    .collect(Collectors.toList());
        return usuariosUDto;
    }

    public UserDto getUserById(Integer id){
        User user = userRepository.findById(id).orElse(null);
        UserDto userDto = userMapper.UserToDtoMap(user);
        return userDto;
    }



}
