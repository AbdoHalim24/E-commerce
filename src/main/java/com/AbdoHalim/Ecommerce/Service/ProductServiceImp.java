package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Entity.Review;
import com.AbdoHalim.Ecommerce.Repository.ProductRepo;
import com.AbdoHalim.Ecommerce.Repository.ReviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{
    private final ProductRepo productRepo;
    private final UserService userService;
    private final ReviewRepo reviewRepo;

    public ProductServiceImp(ProductRepo productRepo,UserService userService,ReviewRepo reviewRepo) {

        this.productRepo = productRepo;
        this.userService=userService;
        this.reviewRepo=reviewRepo;
    }

    @Override
    public List<Product> retriveAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> viewAllCategoryProducts(String categoryName) {
        return null;
    }

    public List<Product> viewAllCategoryProduct(String categoryName) {
        return productRepo.findAllByCategoryName(categoryName);
    }

    @Override
    public List<Review> getallViews(long id) {
        return reviewRepo.findAllByProductId(id);

    }

    @Override
    public Optional<Product> retriveProductById(int id) {

        return productRepo.findById((long) id) ;
    }

    @Override
    public List<Product> searchByProductName(String productName) {
        return productRepo.findAllByProductName(productName.toLowerCase());
    }


}
