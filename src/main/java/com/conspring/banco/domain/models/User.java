package com.conspring.banco.domain.models;
import javax.persistence.*;

// @Table (name = "USUARIOS") -> si se quiere un nombre dist. a la Tabla de la Clase
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
