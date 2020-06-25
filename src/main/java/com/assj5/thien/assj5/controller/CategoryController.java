package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.service.CategoryService;
import com.assj5.thien.assj5.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category/")
public class CategoryController {

    @Autowired
    private ProductSevice productSevice;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public String findAllProductByCategoryId(@PathVariable Long id, @PageableDefault(size = 10)Pageable pageable, Model model){
        Page<Product> products = productSevice.findAllByCategoryId(pageable,id);
        model.addAttribute("category",categoryService.findAll());
        model.addAttribute("product",products);
        return "product-listing";
    }

}
