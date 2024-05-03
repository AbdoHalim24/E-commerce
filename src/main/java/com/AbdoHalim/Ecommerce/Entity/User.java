package com.AbdoHalim.Ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
    @Embedded
    private Address address;
    @OneToOne(cascade =  CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name="cartId",
            referencedColumnName = "cartId"
    )
    private Cart cart;

}
