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

    public List<User> getUsers(){
            List<UserDto> usuariosDto = userRepository.findAll();
            List<User> usuariosUser = usuariosDto.stream()
                    .map(userMapper::DtoToUserMap)
                    .collect(Collectors.toList());
        return usuariosUser;
    }

    public User getUserById(Integer id){
        UserDto userDto = userRepository.findById(id).orElse(null);
        User user = userMapper.DtoToUserMap(userDto);
        return user;
    }



}
