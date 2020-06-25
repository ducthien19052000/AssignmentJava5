package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {

    @Query("select p from ProductDetail p where p.products.productId =:prId")
    List<ProductDetail> findAllByProductId(@Param("prId") Long id);
}


