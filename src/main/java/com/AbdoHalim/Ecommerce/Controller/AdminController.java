package com.AbdoHalim.Ecommerce.Controller;

import com.AbdoHalim.Ecommerce.Entity.Category;
import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Model.CategoryModel;
import com.AbdoHalim.Ecommerce.Model.ProductModel;
import com.AbdoHalim.Ecommerce.Service.AdminService;
import com.AbdoHalim.Ecommerce.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<User> retriveAllUsers() {
        return adminService.RetriveAllUsers();
    }

    @GetMapping("/user/{id}")
    public Optional<User> retriveUserById(@PathVariable int id) {
        return adminService.findByUsreId(id);
    }

    @DeleteMapping("/user/{id}")
    public String DeleteUser(@PathVariable int id) {
        return adminService.DeleteUserByd(id);
    }

    @GetMapping("/User/{email}")
    public User GetUSerByEmail(@PathVariable String email) {
        return adminService.findUserByEmail(email);
    }

    @PostMapping("/category")
    public String AddNewCategory(@RequestBody CategoryModel categoryModel) {
        String CategoryName = categoryModel.getCategoryName();
        if (CategoryName == null || CategoryName.isEmpty()) {
            return "Enter a valid Category Name";
        } else {
            return adminService.AddCategory(CategoryName.toLowerCase());
        }
    }
    @DeleteMapping("category/{CategoryName}")
    public String DeleteCategory(@PathVariable String CategoryName) {
        return adminService.DeleteCategory(CategoryName.toLowerCase());
    }
    @GetMapping("/grant/{id}")
    public String GrantUserToBrand(@PathVariable int id) {
        return adminService.grantUserToBrand((long) id);
    }
    @GetMapping("/brands")
    public List<User> retriveAllBrands(){
        if (adminService.findAllBrands().isEmpty())
            return null;
        return adminService.findAllBrands();
    }
}
