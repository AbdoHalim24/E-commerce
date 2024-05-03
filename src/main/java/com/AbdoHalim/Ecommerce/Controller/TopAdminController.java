package com.AbdoHalim.Ecommerce.Controller;

import com.AbdoHalim.Ecommerce.Entity.User;
import com.AbdoHalim.Ecommerce.Service.TopAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topadmin")
public class TopAdminController {

    private TopAdminService topAdminService;

    public TopAdminController(
            TopAdminService topAdminService) {
        this.topAdminService = topAdminService;
    }
    @GetMapping("/admins")
    public List<User> retriveAllAdmins(){
        return topAdminService.retriveAllAdmins();
    }

    @DeleteMapping("/admin/{id}")
    public String DeleteAdmin(@PathVariable int id){
        return topAdminService.DeleteAdminById(id);
    }
    @GetMapping("/grant/{id}")
    public String GrantPrevilasionToUser(@PathVariable int id){
        return topAdminService.GrantUser(id);
    }

}
