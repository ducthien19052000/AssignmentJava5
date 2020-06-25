package com.assj5.thien.assj5.service;

import com.assj5.thien.assj5.model.Bill;


import java.util.List;

public interface BillService {
    List<Bill> findAll();

    Bill findByID(Long Id);

    void save(Bill bill);

    void update(Bill bill);

    void delete(Long id);



    void saveBySession(Bill bill);
}
