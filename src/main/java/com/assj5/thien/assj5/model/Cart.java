package com.assj5.thien.assj5.model;

import lombok.Data;

@Data
public class Cart {
    private Product product;
    private int quantity;
    private Size size;
    private int productTotal;

    public Cart() {
    }

    public Cart(Product product, int quantity, Size size, int productTotal) {
        this.product = product;
        this.quantity = quantity;
        this.size = size;
        this.productTotal = productTotal;
    }
}
