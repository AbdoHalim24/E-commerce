package com.AbdoHalim.Ecommerce.Model;

import com.AbdoHalim.Ecommerce.Entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressModel {
    private UserModel userModel;
    private Address address;
}
