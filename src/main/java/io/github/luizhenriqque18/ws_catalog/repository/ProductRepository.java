package io.github.luizhenriqque18.ws_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.luizhenriqque18.ws_catalog.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
