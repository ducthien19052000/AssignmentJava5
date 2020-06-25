package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {

  @Query( "select r from Bill r where r.user.userId =:userId order by r.billDate desc ")
        List<Bill> fillAllReceiptByMemberId(@Param("userId") Long Id);

}
