package io.github.luizhenriqque18.ws_catalog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.luizhenriqque18.ws_catalog.entity.Product;
import io.github.luizhenriqque18.ws_catalog.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Product> findAll(PageRequest pageable) {
        return repository.findAll(pageable);
    }
}
