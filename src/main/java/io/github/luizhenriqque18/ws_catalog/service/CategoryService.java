package io.github.luizhenriqque18.ws_catalog.service;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.luizhenriqque18.ws_catalog.entity.Category;
import io.github.luizhenriqque18.ws_catalog.repository.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category Category) {
        return repository.save(Category);
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Category> findAll(PageRequest pageable) {
        return repository.findAll(pageable);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
