package com.conspring.banco.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDto {

    private Long id;
    private int num_cuenta;
    private BigDecimal saldo;

}
