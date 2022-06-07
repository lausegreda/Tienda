package com.Tienda.controller;

import com.Tienda.dao.ClienteDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.Tienda.domain.Cliente;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@Slf4j

public class IndexController {
    
    @Autowired
    private ClienteDao ClienteDao;

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
        
        var clientes = ClienteDao.findAll();
        model.addAttribute("clientes", clientes);
        

        return "index";
    }

}
