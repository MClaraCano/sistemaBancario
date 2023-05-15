package com.conspring.banco.models;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    //conecta con capa de presentaicón, front
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;


    //@Column(name = "FECHA") // nombre de columna en BBDD
    //@Temporal(TemporalType.TIMESTAMP) //reemplaza el Date de Java
    //private Date date;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
