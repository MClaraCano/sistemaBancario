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
    private int cuenta_origen;
    private int cuenta_destino;
    private Date fecha;
    private BigDecimal monto;

}
