package com.assj5.thien.assj5.service.serviceImpl;

import com.assj5.thien.assj5.model.Bill;
import com.assj5.thien.assj5.repository.BillRepository;
import com.assj5.thien.assj5.repository.repositoryImpl.CheckOutRepository;
import com.assj5.thien.assj5.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CheckOutRepository checkOutRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findByID(Long Id) {
        return billRepository.findById(Id).get();
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void update(Bill bill) {
        billRepository.save(bill);

    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);

    }

    @Override
    public void saveBySession(Bill bill) {
        checkOutRepository.save(bill);
    }
}
