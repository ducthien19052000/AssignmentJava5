package com.assj5.thien.assj5.service;


import com.assj5.thien.assj5.model.Size;

import java.util.List;

public interface SizeSevice {
    List<Size> findAll();

    Size findByID(Long Id);

    void save(Size size);

    void update(Size size);

    void delete(Long Id);
}
