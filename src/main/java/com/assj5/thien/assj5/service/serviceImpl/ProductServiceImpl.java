package com.assj5.thien.assj5.service.serviceImpl;


import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.repository.ProductRepository;
import com.assj5.thien.assj5.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductSevice {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findMen(Pageable pageable) {
        return productRepository.findMen(pageable);
    }

    @Override
    public Page<Product> findWomen(Pageable pageable) {
        return productRepository.findWomen(pageable);
    }

    @Override
    public Page<Product> findSale(Pageable pageable) {
        return productRepository.findSale(pageable);
    }

    @Override
    public Product findByID(Long Id) {
        return productRepository.findById(Id).get();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long Id) {
        productRepository.deleteById(Id);
    }

    @Override
    public Page<Product> findAllByCategoryId(Pageable pageable,Long id) {
        return productRepository.findAllByCategoryId(pageable,id);
    }
}
