package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prodcutDetailId;
    private Integer productAmount;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size sizes;

    public ProductDetail() {
    }
}
