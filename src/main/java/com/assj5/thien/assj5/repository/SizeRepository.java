package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SizeRepository extends JpaRepository<Size,Long> {

    @Query("select s from Size s where s.sizeName =:size")
    Size findBySizeName(@Param("size") String size);
}
