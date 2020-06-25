package com.assj5.thien.assj5.service;

import com.assj5.thien.assj5.model.BillDetail;
import com.assj5.thien.assj5.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSevice {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByCategoryId(Pageable pageable,Long id);

    Page<Product> findMen(Pageable pageable);

    Page<Product> findWomen(Pageable pageable);

    Page<Product> findSale(Pageable pageable);

    Product findByID(Long Id);

    void save(Product product);

    void update(Product product);

    void delete(Long Id);
}
