package com.Tienda.service;

import com.Tienda.dao.CategoriaDao;
import com.Tienda.domain.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Siempre que creemos una implementación de interfaz debemos colocar esto, para indicar que este es el que responderá al llamado
public class CategoriaServiceImpl implements CategoriaService { 
    
/*  Pasos:
    1. Cuando ponemos "implements CategoriaService" nos da la opción de implementar todos los métodos abstractos, se hace.
    2. Se genera el @Autowired y se instancia CategoriaDao
    3. Se hacen los cambios necesarios en los métodos para que hagan lo que se requiere.
    */    

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true) //colocar en todos los get, por temas de rendimiento para no bloquear la tabla
    public List<Categoria> getCategorias(boolean activo) { //activo=true (BD)
        var lista=(List<Categoria>)categoriaDao.findAll(); //Count=4 (3 true y 1 false)
    
        if (activo) { //si se cumple
            lista.removeIf(e -> !e.isActivo()); //remueve cuando no sea activo 3 (true)
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null); //orElse es para indicar sino encuentra el registro retorne null 
    }                                                                    //ya que con Find sino encuentra, devuelve una lista vacía

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

}

