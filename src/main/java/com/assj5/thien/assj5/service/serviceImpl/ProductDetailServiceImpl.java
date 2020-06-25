package com.assj5.thien.assj5.service.serviceImpl;

import com.assj5.thien.assj5.model.ProductDetail;
import com.assj5.thien.assj5.repository.ProductDetailRepository;
import com.assj5.thien.assj5.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {


    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail findByID(Long Id) {
        return productDetailRepository.findById(Id).get();
    }

    @Override
    public void save(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }

    @Override
    public void update(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }

    @Override
    public void delete(Long Id) {
        productDetailRepository.deleteById(Id);
    }
}
