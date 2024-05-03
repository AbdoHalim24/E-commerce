package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.Category;
import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Model.ProductModel;
import com.AbdoHalim.Ecommerce.Repository.CategoryRepo;
import com.AbdoHalim.Ecommerce.Repository.ProductRepo;
import com.AbdoHalim.Ecommerce.Repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceimp implements AdminService{
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    public AdminServiceimp(UserRepo userRepo,CategoryRepo categoryRepo,ProductRepo productRepo) {
        this.userRepo = userRepo;
        this.categoryRepo=categoryRepo;
        this.productRepo =productRepo;
    }

    @Override
    public List<User> RetriveAllUsers() {
        return userRepo.findAllUsers();
    }
    @Override
    public Optional<User> findByUsreId(int id) {
        return userRepo.findUserById((long) id);
    }
    @Override
    public String DeleteUserByd(int id){
        Optional<User> user=userRepo.findById((long) id);
        if (!user.isPresent())
            return "userNot  Found";
        if(!user.get().getRole().equals("USER"))
            return "No User With this ID";
        userRepo.deleteById((long) id);
        return "User Deleted Successfully";
    }
    @Override
    public String AddCategory(String categoryName) {
        Category category=categoryRepo.findByCategoryName(categoryName);
        if (category !=null){
            return "This Category is Already Exist";
        }
        Category cate=new Category();
        cate.setCategoryName(categoryName);
        categoryRepo.save(cate);
        return "Category Added Successfully";
    }
    @Override
    public List<Category> RetriveAllCategorys() {
        return categoryRepo.findAll();
    }
    @Override
    public String DeleteCategory(String categoryName) {
        Category category=categoryRepo.findByCategoryName(categoryName);
        if (category==null){
            return " this Category Dosen't Exist";
        }
        categoryRepo.deleteByCategoryName(categoryName);
        return "Category Deleted Successfully";
    }

    @Override
    public String grantUserToBrand(long id) {
       Optional<User> user=userRepo.findById(id);
       if (!user.isPresent()){
           return "user Not Found";
       }
       if (!user.get().getRole().equalsIgnoreCase("USER"))
           return "It is Not  User";

       user.get().setRole("BRAND");
       userRepo.save(user.get());

       return "User Grated To Brand Successfully";
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> findAllBrands() {

        return userRepo.findAllBrands() ;
    }


}
