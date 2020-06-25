package com.assj5.thien.assj5.service.serviceImpl;

import com.assj5.thien.assj5.model.Size;
import com.assj5.thien.assj5.repository.SizeRepository;
import com.assj5.thien.assj5.service.SizeSevice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SizeSeviceImpl implements SizeSevice {
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findByID(Long Id) {
        return sizeRepository.findById(Id).get();
    }

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void update(Size size) {
        sizeRepository.save(size);

    }

    @Override
    public void delete(Long Id) {
        sizeRepository.deleteById(Id);
    }
}
