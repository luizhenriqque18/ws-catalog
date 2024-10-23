package io.github.luizhenriqque18.ws_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.luizhenriqque18.ws_catalog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
