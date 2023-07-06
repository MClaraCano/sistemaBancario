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

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private Account cuenta_origen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private Account cuenta_destino;

    private Date fecha;

    private BigDecimal saldo;

}
