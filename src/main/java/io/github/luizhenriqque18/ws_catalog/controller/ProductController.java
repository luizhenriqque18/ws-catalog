package io.github.luizhenriqque18.ws_catalog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Olá, Mundo!");
    }
}
