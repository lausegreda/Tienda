package com.Tienda.controller;

import com.Tienda.domain.Articulo;
import com.Tienda.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticuloController {

    @Autowired
    private ArticuloService articuloservice;

    @GetMapping("/articulo/listado")
    public String inicio(Model model) {
        var articulos = articuloservice.getArticulos(false); 
        model.addAttribute("articulos", articulos);
        return "/articulos/listado";
    }

    @GetMapping("/articulo/nuevo")
    public String nuevoArticulo(Articulo articulo) {

        return "articulo/modificar";
    }

    @PostMapping("/articulo/guardar") //el que se encarga de guardar la info
    public String guardarArticulo(Articulo articulo) {
        articuloservice.save(articulo);
        return "redirect:/articulo/listado";
    }

    @GetMapping("/articulo//modificar/{idArticulo}")
    public String modificarArticulo(Articulo articulo, Model model) {
        articulo = articuloservice.getArticulo(articulo);
        model.addAttribute("articulo", articulo);
        return "/articulo/modificar";
    }

    @GetMapping("/articulo/eliminar/{idArticulo}")
    public String eliminarArticulo(Articulo articulo) {
        articuloservice.delete(articulo);
        return "redirect:/articulo/listado";
    }
}

