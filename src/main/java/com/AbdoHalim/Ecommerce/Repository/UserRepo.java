package com.AbdoHalim.Ecommerce.Repository;

import com.AbdoHalim.Ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByUserName(String userName);
    @Query(value = "SELECT * FROM ecommerce.user where role =\"USER\" ",nativeQuery = true)

    List<User> findAllUsers();
    @Query(value = "SELECT * FROM ecommerce.user where role =\"ADMIN\" ",nativeQuery = true)
    List<User> findAllAdmins();
    @Query(value = "SELECT * FROM ecommerce.user where role =\"USER\"  and  user_id=?1",nativeQuery = true)
    Optional<User> findUserById(long id);
    @Query(value = "SELECT * FROM ecommerce.user where role =\"BRANDS\" ",nativeQuery = true)
    List<User> findAllBrands();
}
