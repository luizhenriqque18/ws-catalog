package io.github.luizhenriqque18.ws_catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
    
    @Column(nullable = false)
    private Double price;

    @Column
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;
}
