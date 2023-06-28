package com.conspring.banco.api.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {

    private Long id;
    private int num_cuenta;
    private BigDecimal saldo;

}
