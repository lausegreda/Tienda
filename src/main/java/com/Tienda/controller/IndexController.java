package com.Tienda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Tienda.domain.Cliente;
import com.Tienda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@Slf4j

public class IndexController {

    @Autowired
    private ClienteService clienteservice;

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Ahora utilizamos MVC");

        /*Cliente cliente = new Cliente("Laura","Segreda Elizondo", "laura.segreda@gmail.com", "7010-4128");
        model.addAttribute("cliente", cliente);
        
        Cliente cliente2 = new Cliente("Laura","Segreda Elizondo", "laura.segreda@gmail.com", "7010-4128");
        Cliente cliente3 = new Cliente("Laura","Segreda Elizondo", "laura.segreda@gmail.com", "7010-4128");
        
        
        var clientes = Arrays.asList(cliente,cliente2,cliente3);
        
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clientes);*/
        //var mensaje = "Estamos en la semana 4";
        //model.addAttribute("mensaje", mensaje);//el atributo es el primer "mensaje"
        var clientes = clienteservice.getClientes();
        model.addAttribute("clientes", clientes);

        return "index";
    }

    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Cliente cliente) {

        return "modificarCliente";                                                                                                                                                
    }

    @PostMapping("/guardarCliente") //el que se encarga de guardar la info
    public String guardarCliente(Cliente cliente) {
        clienteservice.save(cliente);
        return "redirect:/"; //referencia al slash del get mapping inicial (línea 19) para que almacene en la BD

    }
    
    @GetMapping("/modificarCliente/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model){
        cliente = clienteservice.getCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "modificarCliente";
    }
    
    @GetMapping("/eliminarCliente/{idCliente}")
    public String eliminarCliente(Cliente cliente){
        clienteservice.delete(cliente);
        return "redirect:/"; //accion default
    }
}
