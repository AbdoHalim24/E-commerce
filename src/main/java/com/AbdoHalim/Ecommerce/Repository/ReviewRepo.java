package com.AbdoHalim.Ecommerce.Repository;

import com.AbdoHalim.Ecommerce.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
    @Query(value = "SELECT * FROM Review WHERE product_id=?1", nativeQuery = true)
    List<Review> findAllByProductId(long id);
}
