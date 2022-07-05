package com.Tienda.service;

import com.Tienda.dao.ClienteDao;
import com.Tienda.dao.CreditoDao;
import com.Tienda.domain.Cliente;
import com.Tienda.domain.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Siempre que creemos una implementación de interfaz debemos colocar esto, para indicar que este es el que responderá al llamado
public class ClienteServiceImpl implements ClienteService { 
    
/*  Pasos:
    1. Cuando ponemos "implements ClienteService" nos da la opción de implementar todos los métodos abstractos, se hace.
    2. Se genera el @Autowired y se instancia ClienteDao
    3. Se hacen los cambios necesarios en los métodos para que hagan lo que se requiere.
    */    

    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private CreditoDao creditoDao;

    @Override
    @Transactional(readOnly = true) //cllocar en todos los get, por temas de rendimiento para no bloquear la tabla
    public List<Cliente> getClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null); //orElse es para indicar sino encuentra el registro retorne null 
    }                                                                    //ya que con Find sino encuentra, devuelve una lista vacía

    @Override
    @Transactional
    public void save(Cliente cliente) {
        Credito credito = cliente.getCredito();
        credito = creditoDao.save(credito);
        cliente.setCredito(credito);
        clienteDao.save(cliente);
    }
    
    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

}
