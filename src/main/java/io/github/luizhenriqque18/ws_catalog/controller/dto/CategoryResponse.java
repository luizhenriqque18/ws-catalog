package io.github.luizhenriqque18.ws_catalog.controller.dto;

import io.github.luizhenriqque18.ws_catalog.entity.Category;

public record CategoryResponse(Long id, String name) {

    public static CategoryResponse fromEntity(Category entity) {
        return new CategoryResponse(entity.getId(), entity.getName());
    }

    public Category toEntity() {
        return new Category(name);
    }
}