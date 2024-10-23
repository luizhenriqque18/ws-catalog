package io.github.luizhenriqque18.ws_catalog.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizhenriqque18.ws_catalog.controller.dto.ApiResponse;
import io.github.luizhenriqque18.ws_catalog.controller.dto.PaginationResponse;
import io.github.luizhenriqque18.ws_catalog.controller.dto.CategoryResponse;
import io.github.luizhenriqque18.ws_catalog.entity.Category;
import io.github.luizhenriqque18.ws_catalog.service.CategoryService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CategoryService service;

    @PostMapping()
    public ResponseEntity<CategoryResponse> create(
        @RequestParam(value = "parentCategoryId", required = false) Long parentCategoryId,
        @RequestBody CategoryResponse response) {
            Optional<Category> categoryParentOptional;
            Category category = response.toEntity();

            if(parentCategoryId != null){
                categoryParentOptional = service.findById(parentCategoryId);
                if(categoryParentOptional.isEmpty()) {
                    return ResponseEntity.status(404).build();
                }
                category.setParentCategory(categoryParentOptional.get());
            }       
            
            return ResponseEntity.ok(CategoryResponse.fromEntity(service.save(category)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "parentCategoryId", required = false) Long parentCategoryId,
            @RequestBody CategoryResponse response) {
        Optional<Category> categoryOptional = service.findById(id);

        if (categoryOptional.isEmpty())
            return ResponseEntity.status(404).body("Categoria não encontrado");

        Optional<Category> categoryParentOptional;

    
        Category category = categoryOptional.get();
        BeanUtils.copyProperties(response, category);

        if(parentCategoryId != null){
            categoryParentOptional = service.findById(parentCategoryId);
            if(categoryParentOptional.isEmpty()) {
                return ResponseEntity.status(404).body("Subcategoria não encontrado");
            }
            category.setParentCategory(categoryOptional.get());
        }

        service.save(category);

        return ResponseEntity.ok().body(category.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {

        Optional<Category> categoryOptional = service.findById(id);

        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Categoria não encontrado");
        }

        service.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> detail(@PathVariable(value = "id") Long id) {

        Optional<Category> categoryOptional = service.findById(id);

        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok().body(CategoryResponse.fromEntity(categoryOptional.get()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> list(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", defaultValue= "id") String sort,
        @RequestParam(value = "direction", defaultValue = "ASC") Direction direction) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<Category> categoryPage = service.findAll(pageable);
        Page<CategoryResponse> result = categoryPage.map(CategoryResponse::fromEntity);
        
        return ResponseEntity.ok(
            new ApiResponse<CategoryResponse>(
                result.getContent(),
                PaginationResponse.fromPage(result)
            )
        );
    }
}
