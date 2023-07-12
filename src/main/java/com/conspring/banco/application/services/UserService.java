package com.conspring.banco.application.services;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.api.mappers.AccountMapper;
import com.conspring.banco.api.mappers.UserMapper;
import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.domain.models.Account;
import com.conspring.banco.domain.models.User;
import com.conspring.banco.api.dtos.UserDto;
import com.conspring.banco.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //private UserMapper userMapper;



    public User buscarPorId(Long id) throws NoSeEncontroE {
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            return user;
        } else {
            throw new NoSeEncontroE("El ID no fue encontrado en la BBDD");
        }
    }

    public List<UserDto> getUsers(){
            List<User> usuariosUser = userRepository.findAll();
            List<UserDto> usuariosUDto = usuariosUser.stream()
                    .map(UserMapper::UserToDtoMap)
                    .collect(Collectors.toList());
        return usuariosUDto;
    }

    public UserDto getUserById(Long id) throws NoSeEncontroE {
        User user = buscarPorId(id);
        UserDto userDto = UserMapper.UserToDtoMap(user);
        return userDto;
    }


/*
    public UserDto getUserById(Long id) throws NoSeEncontroE {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new NoSeEncontroE("No existe el ID " + id);
        }
        UserDto userDto = UserMapper.UserToDtoMap(user);
        return userDto;
    }

 */


    public UserDto createUser (UserDto userDto){
        User user = userRepository.save(UserMapper.DtoToUserMap(userDto));
        userDto = UserMapper.UserToDtoMap(user);
        return userDto;
    }




    public UserDto modificarUser(UserDto userDto) throws NoSeEncontroE {

            if (buscarPorId(userDto.getId()) != null){
                User user = userRepository.save(User.builder()
                        .id(userDto.getId())
                        .username(userDto.getUsername())
                        .password(userDto.getPassword())
                        .cuentas_usuario(userDto.getCuentas_usuario())
                        .build());
                userDto = UserMapper.UserToDtoMap(user);
                return userDto;
            } else {
                throw new  NoSeEncontroE("No existe el usuario con ID " + userDto.getId());
            }
    }

    public String borrarById (Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "Usuario eliminado exitosamente";
        } else {
            return "El ID a eliminar no existe";
        }
    }


    //Agregar una cuenta al usuario
    public UserDto agregarCuenta(AccountDto accountDto, Long id) throws NoSeEncontroE {

        //1. Buscar usuario por ID y convertirlo a DTO
        User user = buscarPorId(id);
        UserDto userDto = UserMapper.UserToDtoMap(user);

        //2. Añadir la cuenta a la lista de user encontrada
        List<Account> listaCuentas = userDto.getCuentas_usuario();

        listaCuentas.add(AccountMapper.dtoToAccount(accountDto));

        //3. Devolver usuario con cuenta agregada
        user.setCuentas_usuario(listaCuentas);

        userRepository.save(UserMapper.DtoToUserMap(userDto));

        //convertir a DTO
        userDto = UserMapper.UserToDtoMap(user);

        return userDto;

    }



}
