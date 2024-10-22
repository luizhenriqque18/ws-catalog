package io.github.luizhenriqque18.ws_catalog.service;

import org.springframework.stereotype.Service;

import io.github.luizhenriqque18.ws_catalog.controller.dto.ProductResponse;
import io.github.luizhenriqque18.ws_catalog.entity.Product;
import io.github.luizhenriqque18.ws_catalog.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    public Product save(ProductResponse response) {
        Product entity = new Product();
        entity.setName(response.name());
        entity.setDescription(response.description());
        entity.setPrice(response.price());

        return repository.save(entity);
    }
}
