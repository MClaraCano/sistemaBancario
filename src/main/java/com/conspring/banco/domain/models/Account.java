package com.conspring.banco.domain.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    private User owner;

    /*
    @OneToMany(mappedBy = "cuenta_origen")
    private List<Transfer> origen_transfer_list;

    @OneToMany(mappedBy = "cuenta_destino")
    private List<Transfer> destino_transfer_list;
     */

}
