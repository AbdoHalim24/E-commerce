package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.Category;
import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Model.*;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    String   addNewUser(UserAddressModel userAddressModel);

    User GetCurrantUser();


    List<Category> RetriveAllCategorys();

    String addProductToCart(Long id, int quantity);

    String deleteFormCart(long id);

    String addReview(long id, ReviewModel reviewModel);

    String DeleteReview(long id);

   // List<Product> viewAllCategoryProducts(long brandid, String categoryName);

    List<Product> viewAllBrandProductsByCategoryName(long brandid, String categoryName);

    String changeUserInfo(UserAddressModel userAddressModel);

    String changePassword(ChangePasswordModel changePasswordModel);

    String Login(LoginModel loginModel);
}
