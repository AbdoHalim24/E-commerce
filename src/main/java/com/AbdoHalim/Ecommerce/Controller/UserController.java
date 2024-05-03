package com.AbdoHalim.Ecommerce.Controller;

import com.AbdoHalim.Ecommerce.Entity.Review;
import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Model.ChangePasswordModel;
import com.AbdoHalim.Ecommerce.Model.ReviewModel;
import com.AbdoHalim.Ecommerce.Model.UserAddressModel;
import com.AbdoHalim.Ecommerce.Model.UserModel;
import com.AbdoHalim.Ecommerce.Service.ProductService;
import com.AbdoHalim.Ecommerce.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public UserService userService;
    public ProductService productService;
    public UserController(UserService userService,ProductService productService) {

        this.userService = userService;
        this.productService=productService;
    }
    @GetMapping("/currantuser")
    public User getCurranUser(){
        return userService.GetCurrantUser();

    }
    //used for add to cart
    //used fo update the product in cart by remove the product and add the updated one
    @GetMapping("cart/product/{id}/quantity/{quantity}")
    public String addAndUpdateProductToCart(@PathVariable int id ,@PathVariable int quantity){
        return userService.addProductToCart((long) id,quantity);
    }

    @DeleteMapping("/cart/product/{id}")
    public String deleteProductFormCart(@PathVariable int id){
        return  userService.deleteFormCart((long) id);
    }

    @PostMapping("/review/product/{id}")
    public String addReviewToProduct(@PathVariable int id, @RequestBody ReviewModel reviewModel){
        return userService.addReview((long) id, reviewModel);
    }
    @DeleteMapping("/review/{id}")
    public  String Deletereview(@PathVariable int id){
        return userService.DeleteReview((long) id);
    }

    // in this  api dont type password  or email
    @PutMapping("/info")
    public String changeInfo(@RequestBody UserAddressModel userAddressModel){
        return userService.changeUserInfo(userAddressModel);
    }
    @PostMapping("/changepassword")
    public String ChangePassword(@RequestBody ChangePasswordModel changePasswordModel){
        return userService.changePassword(changePasswordModel);
    }









}
