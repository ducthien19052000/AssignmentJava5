package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetailId;
    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    private int amount;
    private double price;

    public BillDetail(Bill bill,Product product, int amount, double price,Size size ) {
        this.bill = bill;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.size = size;
    }
}
