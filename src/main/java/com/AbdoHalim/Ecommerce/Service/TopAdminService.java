package com.AbdoHalim.Ecommerce.Service;


import com.AbdoHalim.Ecommerce.Entity.User;

import java.util.List;

public interface TopAdminService {



    List<User> retriveAllAdmins();

    String DeleteAdminById(int id);

    String GrantUser(int id);
}
