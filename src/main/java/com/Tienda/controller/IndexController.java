package com.Tienda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Tienda.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@Slf4j

public class IndexController {

    @Autowired
    private ArticuloService articuloservice;

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Ahora utilizamos MVC");
        
        var articulos = articuloservice.getArticulos(true);
        model.addAttribute("articulos", articulos);

        return "index";
    }
}
