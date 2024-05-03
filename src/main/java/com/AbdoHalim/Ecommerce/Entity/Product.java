package com.AbdoHalim.Ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long productId;
    private String productName;
    private String description;
    private float productPrice;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "categoryName", nullable = false)
    private Category category;
    @ManyToOne( fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;
    @OneToMany
    @JsonIgnore
    @JoinColumn(name="productId",referencedColumnName = "productId")
    private List<Review> reviews;



}
