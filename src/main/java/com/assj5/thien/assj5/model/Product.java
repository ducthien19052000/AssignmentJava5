package com.assj5.thien.assj5.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private double productPrice;
    private String productDesc;
    private String productImg1;
    private String productImg2;
    private String productImg3;
    private String productImg4;
    private double productSale;
    private boolean productStatus;
    private boolean sex;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private List<ProductDetail> productDetails;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private List<BillDetail> billDetails;
    public Product() {
    }
}
