package io.github.luizhenriqque18.ws_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.luizhenriqque18.ws_catalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
