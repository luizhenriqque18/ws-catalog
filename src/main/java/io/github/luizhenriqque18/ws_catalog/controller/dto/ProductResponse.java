package io.github.luizhenriqque18.ws_catalog.controller.dto;

import io.github.luizhenriqque18.ws_catalog.entity.Product;

public record ProductResponse(Long id, String name, String description, Double price) {

    public static ProductResponse fromEntity(Product entity) {
        return new ProductResponse(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice());
    }

    public Product toEntity() {
        return new Product(name, description, price);
    }
}
