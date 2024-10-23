package io.github.luizhenriqque18.ws_catalog.controller.dto;

import io.github.luizhenriqque18.ws_catalog.entity.Category;
import io.github.luizhenriqque18.ws_catalog.entity.Product;

public record ProductResponse(Long id, String name, String description, Double price,
        CategoryResponse categoryResponse) {

    public static ProductResponse fromEntity(Product entity) {
        return new ProductResponse(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(),
                CategoryResponse.fromEntity(entity.getCategoria() != null ? entity.getCategoria() : new Category()));
    }

    public Product toEntity() {
        if(categoryResponse.id() == null)
            return new Product(name, description, price);
        else
            return new Product(name, description, price, categoryResponse.toEntity());
    }
}
