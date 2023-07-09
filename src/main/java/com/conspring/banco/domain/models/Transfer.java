package com.conspring.banco.domain.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transfer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "transfer_id")
    Long id;

    private int cuenta_origen;
    private int cuenta_destino;
    private Date fecha;
    private BigDecimal monto;



}
