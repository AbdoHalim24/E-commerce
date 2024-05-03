package com.AbdoHalim.Ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Address {

    private String country;
    private String city;
    private String street;
}
