package com.conspring.banco.domain.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num_cuenta;

    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
