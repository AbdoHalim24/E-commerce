package com.AbdoHalim.Ecommerce.Repository;

import com.AbdoHalim.Ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByProductName(String lowerCase);
    @Query(value = "SELECT * FROM Product WHERE product_name LIKE %?1% and user_id=?2", nativeQuery = true)
    Product findByProductNameInTheBrand(String lowerCase, Long id);
    @Query(value = "SELECT * FROM Product WHERE product_name LIKE %?1% ", nativeQuery = true)
    List<Product> findAllByProductName(String lowerCase);

    @Query(value = "SELECT * FROM Product WHERE user_id=?1", nativeQuery = true)
    List<Product> findByBrandId(Long userId);

    @Query(value = "SELECT * FROM Product WHERE category_name = ?1 and user_id=?2", nativeQuery = true)
    List<Product> findAllByCategoryNameInTheBrand(String categoryName, Long userId);

    @Query(value = "SELECT * FROM Product WHERE product_id = ?1 and user_id=?2", nativeQuery = true)
    Product findByProductInTheBrand(long id, Long userId);

    @Query(value = "SELECT * FROM Product WHERE category_name = ?1", nativeQuery = true)

    List<Product> findAllByCategoryName(String categoryName);
}