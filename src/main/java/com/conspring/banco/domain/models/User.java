package com.conspring.banco.domain.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// @Table (name = "USUARIOS") -> si se quiere un nombre dist. a la Tabla de la Clase
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table (name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    //Al generar un usuario, se genere automáticamente Account
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> cuentas_usuario;

    //mappedBy: podemos establecer una relación bidireccional
    // ya que a pesar de tener una única FK, podemos relacionar ambas tablas.

    //Normalmente el owning side en este tipo de relaciones suele estar
    // en el @ManyToOne y el mappedBy iría en la entidad padre.

    //cascade: Cuando realizamos alguna acción en la entidad objetivo,
    // la misma acción se aplicará automáticamente a sus entidades asociadas.

    // - orphanRemoval = true | cada vez que eliminemos un User
    // automáticamente se eliminarán todos las accounts que están asociados a él
    // - orphanRemoval es un atributo específico del ORM que marca la entidad secundaria
    // a eliminar cuando ya no se haga referencia a ella desde la entidad principal.
    // Por defecto se encuentra desactivado, es decir, a false.
    // Se elimina la entidad, y la asociación con ella







    //@Column(name = "FECHA") // nombre de columna en BBDD
    //@Temporal(TemporalType.TIMESTAMP) //reemplaza el Date de Java
    //private Date date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Account> getCuentas_usuario() {
        return cuentas_usuario;
    }

    public void setCuentas_usuario(List<Account> cuentas_usuario) {
        this.cuentas_usuario = cuentas_usuario;
    }
}
