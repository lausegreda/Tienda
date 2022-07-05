package com.Tienda.service;

import com.Tienda.domain.Articulo;
import java.util.List;

public interface ArticuloService { //Las interfaces definen métodos que serán estandar y que las clases que los implementan
                                  //tienen que a fuerza implementarlos aunque sea vacíos.
                                  //solo se colocan los métodos
   
    public List<Articulo> getArticulos(boolean activo); //siempre que se va a importar una lista, es recomendable colocar el nombre el plural "getarticulos"
    
    public Articulo getArticulo(Articulo articulo); //cuando es uno solo, es mejor colocarlo en singular
    
    public void save(Articulo articulo); //se usa para insertar y modificar a la vez, cuando el id es 0, lo toma como nuevo y cuando tiene id lo actualiza
    
    public void delete(Articulo articulo);
    
}