package com.AbdoHalim.Ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long cartId;

    @ManyToMany
    @JoinTable(
            name = "ProductsInCart",
            joinColumns = @JoinColumn(
                    name="cartId",
                    referencedColumnName = "cartId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "productId",
                    referencedColumnName = "productId"
            )
    )
    private List<Product> products;




}
