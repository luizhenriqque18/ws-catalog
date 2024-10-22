package io.github.luizhenriqque18.ws_catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Olá, Mundo!");
    }
}
