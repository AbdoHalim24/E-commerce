package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.Category;
import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<User> RetriveAllUsers();

    Optional<User> findByUsreId(int id);

    String DeleteUserByd(int id);

    String AddCategory(String categoryName);

    List<Category> RetriveAllCategorys();

    String DeleteCategory(String categoryName);


    String grantUserToBrand(long id);

    User findUserByEmail(String email);

    List<User> findAllBrands();
}
