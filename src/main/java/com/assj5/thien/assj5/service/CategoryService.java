package com.assj5.thien.assj5.service;


import com.assj5.thien.assj5.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByID(Long Id);

    void save(Category category);

    void update(Category category);

    void delete(Long Id);
}
