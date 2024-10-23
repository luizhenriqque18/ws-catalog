package io.github.luizhenriqque18.ws_catalog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizhenriqque18.ws_catalog.controller.dto.ApiResponse;
import io.github.luizhenriqque18.ws_catalog.controller.dto.PaginationResponse;
import io.github.luizhenriqque18.ws_catalog.controller.dto.ProductResponse;
import io.github.luizhenriqque18.ws_catalog.entity.Product;
import io.github.luizhenriqque18.ws_catalog.service.ProductService;

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

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping()
    public ResponseEntity<ProductResponse> create(@RequestBody ProductResponse response) {
        Product product = service.save(response.toEntity());
        return ResponseEntity.ok(ProductResponse.fromEntity(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable(value = "id") Long id,
            @RequestBody ProductResponse response) {
        Optional<Product> productOptional = service.findById(id);

        if (productOptional.isEmpty())
            return ResponseEntity.status(404).body("Produto não encontrado");

        Product product = productOptional.get();
        BeanUtils.copyProperties(response, product);
        service.save(product);

        return ResponseEntity.ok().body(product.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {

        Optional<Product> productOptional = service.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Produto não encontrado");
        }

        service.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> detail(@PathVariable(value = "id") Long id) {

        Optional<Product> productOptional = service.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok().body(ProductResponse.fromEntity(productOptional.get()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProductResponse>> list(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", defaultValue= "id") String sort,
        @RequestParam(value = "direction", defaultValue = "ASC") Direction direction) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<Product> productPage = service.findAll(pageable);
        Page<ProductResponse> result = productPage.map(ProductResponse::fromEntity);
        
        return ResponseEntity.ok(
            new ApiResponse<ProductResponse>(
                result.getContent(),
                PaginationResponse.fromPage(result)
            )
        );
    }
}
