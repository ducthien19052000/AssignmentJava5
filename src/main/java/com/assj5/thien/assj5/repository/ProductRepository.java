package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.productStatus = true")
    Page<Product> findAll(Pageable pageable);

    @Query("select p from  Product p where p.sex = false and p.productStatus=true ")
    Page<Product> findMen(Pageable pageable);

    @Query("select p from  Product p where p.sex = true and p.productStatus=true ")
    Page<Product> findWomen(Pageable pageable);

    @Query("select p from  Product p where p.productStatus =true and p.productSale > 0 order by p.productSale desc ")
    Page<Product> findSale(Pageable pageable);

    @Query("select p from Product p where p.category.categoryId =:ctId order by p.productId desc ")
    Page<Product> findAllByCategoryId(Pageable pageable,@Param("ctId") Long id);
}
