package com.assj5.thien.assj5.service;


import com.assj5.thien.assj5.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> findAll();

    ProductDetail findByID(Long Id);

    void save(ProductDetail productDetail);

    void update(ProductDetail productDetail);

    void delete(Long Id);
}
