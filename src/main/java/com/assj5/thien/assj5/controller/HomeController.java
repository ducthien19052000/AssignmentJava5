package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.repository.ProductRepository;
import com.assj5.thien.assj5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String hello(Model model, @PageableDefault (size = 8) Pageable pageable){
        model.addAttribute("productTop8",productRepository.findAll(pageable));
        model.addAttribute("productMen",productRepository.findMen(pageable));
        model.addAttribute("productWomen",productRepository.findWomen(pageable));
        model.addAttribute("productSale",productRepository.findSale(pageable));
        model.addAttribute("category",categoryService.findAll());
        return "index";
    }
}
