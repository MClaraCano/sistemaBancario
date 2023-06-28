package com.conspring.banco.application.services;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.api.mappers.AccountMapper;
import com.conspring.banco.api.mappers.UserMapper;
import com.conspring.banco.domain.models.Account;
import com.conspring.banco.domain.models.User;
import com.conspring.banco.api.dtos.UserDto;
import com.conspring.banco.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //private UserMapper userMapper;


    public List<UserDto> getUsers(){
            List<User> usuariosUser = userRepository.findAll();
            List<UserDto> usuariosUDto = usuariosUser.stream()
                    .map(UserMapper::UserToDtoMap)
                    .collect(Collectors.toList());
        return usuariosUDto;
    }

    public UserDto getUserById(Integer id){
        User user = userRepository.findById(id).orElse(null);
        UserDto userDto = UserMapper.UserToDtoMap(user);
        return userDto;
    }

    public UserDto createUser (UserDto userDto){
        User user = userRepository.save(UserMapper.DtoToUserMap(userDto));
        userDto = UserMapper.UserToDtoMap(user);
        return userDto;
    }

    public UserDto modificarUser(UserDto userDto) {
        User user = userRepository.save(User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build());
        userDto = UserMapper.UserToDtoMap(user);
        return userDto;
    }

    public void borrarById (Integer id){
        userRepository.deleteById(id);
    }



}
