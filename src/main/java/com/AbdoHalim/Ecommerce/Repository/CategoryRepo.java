package com.AbdoHalim.Ecommerce.Repository;

import com.AbdoHalim.Ecommerce.Entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,String> {

    @Modifying
    @Transactional
    void deleteByCategoryName(String categoryName);



    Category findByCategoryName(String categoryName);



}
