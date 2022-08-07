package com.Tienda.controller;

import com.Tienda.domain.Articulo;
import com.Tienda.service.ArticuloService;
import com.Tienda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticuloController {

    @Autowired
    private ArticuloService articuloservice;
    
    @Autowired
    private CategoriaService categoriaservice;

    @GetMapping("/articulo/listado")
    public String inicio(Model model) {
        var articulos = articuloservice.getArticulos(false);
        
        model.addAttribute("totalArticulos",articulos.size());
        
        model.addAttribute("articulos", articulos);
        return "/articulo/listado";
    }

    @GetMapping("/articulo/nuevo")
    public String nuevoArticulo(Articulo articulo, Model model) {
        var categorias = categoriaservice.getCategorias(true);
        model.addAttribute("categorias", categorias);
        return "/articulo/modificar";
    }

    @PostMapping("/articulo/guardar") //el que se encarga de guardar la info
    public String guardarArticulo(Articulo articulo) {
        articuloservice.save(articulo);
        return "redirect:/articulo/listado";
    }

    @GetMapping("/articulo/modificar/{idArticulo}")
    public String modificarArticulo(Articulo articulo, Model model) {
        var categorias = categoriaservice.getCategorias(false); //false trae todas las categorias
        model.addAttribute("categorias", categorias);
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

