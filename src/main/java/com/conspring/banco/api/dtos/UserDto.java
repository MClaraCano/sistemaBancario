package com.conspring.banco.api.dtos;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

    private Integer id;
    private String username;
    private String password;

}
