package com.AbdoHalim.Ecommerce.Service;


import com.AbdoHalim.Ecommerce.Entity.Product;
import com.AbdoHalim.Ecommerce.Model.ProductModel;

import java.util.List;

public interface BrandService {


    String addProduct(ProductModel productModel);

    String updateProduct(long id, ProductModel productModel);

    String DeleteProduct(long id);
}
