package com.AbdoHalim.Ecommerce.Controller;

import com.AbdoHalim.Ecommerce.Entity.Category;
import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Entity.Review;
import com.AbdoHalim.Ecommerce.Model.LoginModel;
import com.AbdoHalim.Ecommerce.Model.UserAddressModel;
import com.AbdoHalim.Ecommerce.Service.ProductService;
import com.AbdoHalim.Ecommerce.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class publicController {
    private UserService userService;
   private ProductService productService;
    public publicController(UserService userService,ProductService productService) {
        this.userService = userService;
        this.productService=productService;
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginModel loginModel){
        return userService.Login(loginModel);
    }

    @PostMapping("/register")
    public String saveNewUser(@RequestBody UserAddressModel userAddressModel){
        String result =userService.addNewUser(userAddressModel);
        return result;
    }
    @GetMapping("/categories")
    public List<Category>RetreiveAllCategories(){
        return userService.RetriveAllCategorys();
    }

    @GetMapping("/products")
    public List<Product> ViewAllProducts(){
        return productService.retriveAllProducts();
    }

    @GetMapping("/ProductsInCategory/{CategoryName}")
    public List<Product> ViewCategoryProducts(@PathVariable String CategoryName){
        return productService.viewAllCategoryProduct(CategoryName.toLowerCase());
    }
    @GetMapping("/product/{id}")
    public Optional<Product> retriveProductById(@PathVariable int id){

        return productService.retriveProductById(id);
    }
    @GetMapping("/Product/{productName}")
    public List<Product> SearchForProduct(@PathVariable String productName){
        return productService.searchByProductName(productName);
    }
    //to view Brand page loop in all category
    @GetMapping("/{brandid}/products/CategoryName/{categoryName}")
    public List<Product> ViewProductsInCategory(@PathVariable int brandid,@PathVariable String categoryName){
        return userService.viewAllBrandProductsByCategoryName((long) brandid,categoryName);
    }
    @GetMapping("/review/product/{id}")
    public List<Review> retriveallreviews(@PathVariable int id){
        return productService.getallViews((long) id);
    }

}
