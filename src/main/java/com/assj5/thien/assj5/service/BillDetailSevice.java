package com.assj5.thien.assj5.service;

import com.assj5.thien.assj5.model.BillDetail;

import java.util.List;

public interface BillDetailSevice {
    List<BillDetail> findAll();

    BillDetail findByID(Long Id);

    void save(BillDetail billDetail);

    void update(BillDetail billDetail);

    void delete(Long Id);

    void saveAll(List<BillDetail> billDetails);
}
