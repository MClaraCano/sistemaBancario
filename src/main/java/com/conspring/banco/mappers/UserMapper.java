package com.conspring.banco.mappers;

import com.conspring.banco.models.User;
import com.conspring.banco.models.dtos.UserDto;

//@UtilityClass // para configurar la clase y poder trabajarla como interfaz para mapear
public class UserMapper {

    //Con @Builder
    public UserDto UserToDtoMap(User user){
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        return userDto;
    }

    //Sin @Builder
    public User DtoToUserMap(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
