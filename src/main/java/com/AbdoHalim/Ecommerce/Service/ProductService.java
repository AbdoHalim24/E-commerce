package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Entity.Review;

import java.util.List;
import java.util.Optional;


public interface ProductService {


    List<Product> retriveAllProducts();

    List<Product> viewAllCategoryProducts(String categoryName);

    Optional<Product> retriveProductById(int id);

    List<Product> searchByProductName(String productName);

    List<Product> viewAllCategoryProduct(String categoryName);

    List<Review> getallViews(long id);
}
