package io.github.luizhenriqque18.ws_catalog.controller.dto;

import io.github.luizhenriqque18.ws_catalog.entity.Product;

public record ProductResponse(String name, String description, Double price) {
    public static ProductResponse fromEntity(Product entity) {
        return new ProductResponse(entity.getName(), entity.getDescription(), entity.getPrice());
    }
}
