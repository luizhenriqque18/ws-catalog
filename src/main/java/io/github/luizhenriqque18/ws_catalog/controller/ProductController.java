package io.github.luizhenriqque18.ws_catalog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizhenriqque18.ws_catalog.controller.dto.ProductResponse;
import io.github.luizhenriqque18.ws_catalog.entity.Product;
import io.github.luizhenriqque18.ws_catalog.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Olá, Mundo!");
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@RequestBody ProductResponse response) {
        Product product = service.save(response);
        
        return ResponseEntity.ok(ProductResponse.fromEntity(product));
    }

}
