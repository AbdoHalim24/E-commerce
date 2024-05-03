package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.Category;
import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Model.ProductModel;
import com.AbdoHalim.Ecommerce.Repository.CategoryRepo;
import com.AbdoHalim.Ecommerce.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImp  implements  BrandService{

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final UserService userService;

    public BrandServiceImp(ProductRepo productRepo,CategoryRepo categoryRepo,UserService userService) {
        this.productRepo = productRepo;
        this.categoryRepo=categoryRepo;
        this.userService=userService;
    }

    @Override
    public String addProduct(ProductModel productModel) {
        if(productModel.getProductName().isEmpty() ||
                productModel.getCategory().isEmpty()||productModel.getQuantity()<=0){
            return "Enter a Valid Fields ";
        }

        Category category=categoryRepo.findByCategoryName(productModel.getCategory().toLowerCase());

        if (category==null){
            return "Enter the Right Category Name";
        }
        Product product= productRepo.findByProductNameInTheBrand(productModel.getProductName().toLowerCase()
                ,userService.GetCurrantUser().getUserId());
        if(product !=null){
            return "this Product Already exist Update it if You Want";
        }
        Product product1=new Product();
        product1.setProductName(productModel.getProductName().toLowerCase());
        product1.setProductPrice(productModel.getProductPrice());
        product1.setCategory(new Category(productModel.getCategory().toLowerCase()));
        product1.setQuantity(productModel.getQuantity());
        product1.setUser(userService.GetCurrantUser());
        productRepo.save(product1);
        return "Product Added Successfully";
    }

    @Override
    public String updateProduct(long id, ProductModel productModel) {
        if(productModel.getProductName().isEmpty() ||
                productModel.getCategory().isEmpty()||productModel.getQuantity()<=0){
            return "Enter a Valid Fields ";
        }
        Category category=categoryRepo.findByCategoryName(productModel.getCategory().toLowerCase());
        if (category==null){
            return "Enter the Right Category Name";
        }
        Product product1=new Product();
        product1.setProductId(id);
        product1.setProductName(productModel.getProductName().toLowerCase());
        product1.setProductPrice(productModel.getProductPrice());
        product1.setCategory(new Category(productModel.getCategory().toLowerCase()));
        product1.setQuantity(productModel.getQuantity());
        product1.setDescription(productModel.getDescription());
        product1.setUser(userService.GetCurrantUser());
        productRepo.save(product1);

        return "Product Updated Successfully";
    }



    @Override
    public String DeleteProduct(long id) {
        Product product=productRepo.findByProductInTheBrand(id,userService.GetCurrantUser().getUserId());
        if (product==null){
            return "this Product Not found";
        }
        productRepo.deleteById(id);

        return "product Deleted Successfully";
    }
}
