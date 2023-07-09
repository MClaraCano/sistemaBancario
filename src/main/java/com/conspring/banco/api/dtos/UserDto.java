package com.conspring.banco.api.dtos;

import com.conspring.banco.domain.models.Account;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

    private Long id;
    private String username;
    private String password;

    private List<Account> cuentas_usuario;
}
