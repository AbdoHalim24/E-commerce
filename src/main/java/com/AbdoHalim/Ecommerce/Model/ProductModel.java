package com.AbdoHalim.Ecommerce.Model;

import com.AbdoHalim.Ecommerce.Entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private String productName;
    private String description;
    private float productPrice;
    private int quantity;
    private String category;
}
