package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopAdminServiceImp implements TopAdminService{
    private UserRepo userRepo;

    public TopAdminServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> retriveAllAdmins() {
        if (userRepo.findAllAdmins().isEmpty())
            return null;
        return userRepo.findAllAdmins();
    }

    @Override
    public String DeleteAdminById(int id) {
        Optional<User> user= userRepo.findById((long) id);

        if(!user.isPresent()){
            return "Admin Not Found";
        }
        userRepo.deleteById((long) id);

        return "ِAdmin Deleted Successfully";
    }

    @Override
    public String GrantUser(int id) {
        Optional<User> user= userRepo.findById((long) id);

        if(!user.isPresent()){
            return "User Not Found";
        }
        user.get().setRole("ADMIN");
        userRepo.save(user.get());
        return "User Granted Successfully";
    }

}
