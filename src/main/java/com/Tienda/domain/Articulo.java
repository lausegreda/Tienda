package com.Tienda.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="articulo")
public class Articulo implements Serializable{
    
     private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idArticulo; //id_articulo
     private Long idCategoria; //id_categoria
     private String descripcion; 
     private String detalle; 
     private double precio;
     private int existencias; 
     private boolean activo; 

    public Articulo() {
    }

    public Articulo(Long idArticulo, Long idCategoria, String descripcion, String detalle, double precio, int existencias, boolean activo) {
        this.idArticulo = idArticulo;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.activo = activo;
    } 
}
