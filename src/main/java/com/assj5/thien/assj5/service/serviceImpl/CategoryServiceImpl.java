package com.assj5.thien.assj5.service.serviceImpl;

import com.assj5.thien.assj5.model.Category;
import com.assj5.thien.assj5.repository.CategoryRepository;
import com.assj5.thien.assj5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByID(Long Id) {
        return categoryRepository.findById(Id).get();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long Id) {
        categoryRepository.deleteById(Id);
    }
}
