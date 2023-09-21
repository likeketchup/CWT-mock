package com.example.concur.entity;

import jakarta.persistence.*;

//@Entity
//@Table(name="user")
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Data
@AllArgsConstructor
@Table(name = "User")
public class User {
    @Id
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Embedded
    private Address address;
    @Embedded
    private Company company;

    public User() {}


}
