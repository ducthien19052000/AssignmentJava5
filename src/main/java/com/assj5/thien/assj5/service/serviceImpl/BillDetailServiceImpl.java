package com.assj5.thien.assj5.service.serviceImpl;



import com.assj5.thien.assj5.model.BillDetail;
import com.assj5.thien.assj5.repository.BillDetailRepository;
import com.assj5.thien.assj5.service.BillDetailSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailSevice  {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetail findByID(Long Id) {
        return billDetailRepository.findById(Id).get();
    }

    @Override
    public void save(BillDetail billDetail) {
        billDetailRepository.save(billDetail);

    }

    @Override
    public void update(BillDetail billDetail) {
        billDetailRepository.save(billDetail);

    }

    @Override
    public void delete(Long Id) {
        billDetailRepository.deleteById(Id);

    }

    @Override
    public void saveAll(List<BillDetail> billDetails) {
        for(BillDetail billDetail : billDetails){
            billDetailRepository.save(billDetail);
        }
    }
}
