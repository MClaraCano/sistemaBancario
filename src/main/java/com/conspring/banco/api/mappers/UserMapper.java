package com.conspring.banco.api.mappers;

import com.conspring.banco.domain.models.User;
import com.conspring.banco.api.dtos.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass // para configurar la clase y poder trabajarla como interfaz para mapear
// Para indicar que es una clase de mapeo de configuración
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
