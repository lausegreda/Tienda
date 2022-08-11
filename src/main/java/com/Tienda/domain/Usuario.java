package com.Tienda.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data@Entity@Table(name="usuario")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario; // Hibernate lo transforma en id_cliente    String username;
    String password;
    Long idCliente;
    Long idRol;
    private String username;
    
    public Usuario() {
    }
    
    public Usuario(String username, String password, long idRol) {
        this.username = username;
        this.password = password;
        this.idRol = idRol;
    }

}
