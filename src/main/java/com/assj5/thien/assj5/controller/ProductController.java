package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.service.CategoryService;
import com.assj5.thien.assj5.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProductController {

    @Autowired
    private ProductSevice productSevice;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("product")
    public String showProduct(Model model, @PageableDefault(size =20 )Pageable pageable){
        model.addAttribute( "product",productSevice.findAll(pageable));
        model.addAttribute("productMen",productSevice.findMen(pageable));
        model.addAttribute("productWomen",productSevice.findWomen(pageable));
        model.addAttribute("productSale",productSevice.findSale(pageable));
        model.addAttribute("category",categoryService.findAll());
        return "product-listing";
    }

    @GetMapping("/productDetail/{id}")
    public  String productDetail(@PathVariable Long id, Model model){
        Product product= productSevice.findByID(id);
        model.addAttribute("category",categoryService.findAll());
        model.addAttribute("product",product);
        return "product-detail";
    }

}
