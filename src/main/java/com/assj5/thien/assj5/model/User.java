package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean role;
    private boolean status;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private List<Bill> bills;

    public User() {
    }
}
