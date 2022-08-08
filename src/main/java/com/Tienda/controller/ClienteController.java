package com.Tienda.controller;

import com.Tienda.domain.Cliente;
import com.Tienda.service.ClienteReportService;
import com.Tienda.service.ClienteService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteservice;

    @GetMapping("/cliente/listado")
    public String inicio(Model model) {
        var clientes = clienteservice.getClientes();

        var limiteTotal = 0;
        for (var c : clientes) {
            limiteTotal += c.getCredito().getLimite();
        }
        model.addAttribute("limiteTotal", limiteTotal);
        model.addAttribute("totalClientes", clientes.size());

        model.addAttribute("clientes", clientes);
        return "/cliente/listado";
    }

    @GetMapping("/cliente/nuevo")
    public String nuevoCliente(Cliente cliente) {

        return "cliente/modificar";
    }

    @PostMapping("/cliente/guardar") //el que se encarga de guardar la info
    public String guardarCliente(Cliente cliente) {
        clienteservice.save(cliente);
        return "redirect:/cliente/listado";
    }

    @GetMapping("/cliente/modificar/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model) {
        cliente = clienteservice.getCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "/cliente/modificar";
    }

    @GetMapping("/cliente/eliminar/{idCliente}")
    public String eliminarCliente(Cliente cliente) {
        clienteservice.delete(cliente);
        return "redirect:/cliente/listado";
    }

    @Autowired
    private ClienteReportService clienteReportService;

    @GetMapping(value = "/cliente/ReporteClientes", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getFile() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(clienteReportService.generateReport()));
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
