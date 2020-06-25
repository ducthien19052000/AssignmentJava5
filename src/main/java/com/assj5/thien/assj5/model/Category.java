package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private List<Product> products;

    public Category() {
    }
}
