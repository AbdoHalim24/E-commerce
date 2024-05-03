package com.AbdoHalim.Ecommerce.Controller;

import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Model.ProductModel;
import com.AbdoHalim.Ecommerce.Service.BrandService;
import com.AbdoHalim.Ecommerce.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    private  final UserService userService;
    public BrandController(BrandService brandService,UserService userService) {
        this.brandService = brandService;
        this.userService=userService;
    }
    @PostMapping("/product")
    public String AddNewProduct(@RequestBody ProductModel productModel){
        return brandService.addProduct(productModel);
    }
    @PutMapping("/product/{id}")
    public String UpdateProduct(@PathVariable int id,@RequestBody ProductModel productModel){
        return brandService.updateProduct((long) id, productModel);
    }
    @DeleteMapping("/product/{id}")
    public String DeleteProductByidInTheBrand(@PathVariable int id){
        return brandService.DeleteProduct((long) id);
    }

}
