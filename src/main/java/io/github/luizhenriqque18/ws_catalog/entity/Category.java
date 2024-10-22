package io.github.luizhenriqque18.ws_catalog.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Product> produtos;
}
