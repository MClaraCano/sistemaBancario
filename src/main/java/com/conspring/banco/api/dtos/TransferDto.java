package com.conspring.banco.api.dtos;

import com.conspring.banco.domain.models.Account;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferDto {

    private long id;
    private Account cuenta_origen;
    private Account cuenta_destino;
    private Date fecha;
    private BigDecimal saldo;

}
