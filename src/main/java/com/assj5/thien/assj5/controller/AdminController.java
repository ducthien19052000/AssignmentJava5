package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Category;
import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.model.User;
import com.assj5.thien.assj5.repository.BillRepository;
import com.assj5.thien.assj5.repository.ProductRepository;
import com.assj5.thien.assj5.repository.UserRepository;
import com.assj5.thien.assj5.service.CategoryService;
import com.assj5.thien.assj5.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("manager/")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String adminHome() {
        return "admin/HomeAdmin";
    }

    @GetMapping("product")
    public String productManager(Model model, @PageableDefault(size = 8) Pageable pageable) {
        model.addAttribute("product", productRepository.findAll(pageable));
        return "admin/ProductManager";
    }

    @GetMapping("category")
    public String categoryManager(Model model) {
        model.addAttribute("category", categoryService.findAll());
        return "admin/Category";
    }

    @GetMapping("addcategory")
    public String addCategotyPage(Model model) {
        model.addAttribute("category", new Category());
        return "admin/addCategory";
    }

    @PostMapping("addcategory")
    public String addCategoty(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/manager/category";
    }

    @GetMapping("updatecategory/{id}")
    public String updateCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.findByID(id));
        return "admin/updateCategory";
    }

    @PostMapping("updatecategory/{id}")
    public String updateCategory(@ModelAttribute Category category, @PathVariable Long id, @PageableDefault(size = 8) Pageable pageable) {
        Page<Product> products = productRepository.findAllByCategoryId(pageable, id);
        Category categoryUpdateProduct = categoryService.findByID(id);
        categoryUpdateProduct.setCategoryName(category.getCategoryName());
        categoryService.update(category);
        for (Product product : products) {
            product.setCategory(categoryUpdateProduct);
            productRepository.save(product);
        }
        return "redirect:/manager/category";
    }


    @GetMapping("receipt")
    public String receiptManager(Model model, @PageableDefault(size = 8) Pageable pageable) {
        model.addAttribute("bill", billRepository.findAll(pageable));
        return "admin/Receipt";
    }

    @GetMapping("user")
    public String userManager(Model model, @PageableDefault(size = 8) Pageable pageable) {
        model.addAttribute("user", userService.findAll());
        return "admin/User";
    }

    @GetMapping("updateuser/{id}")
    public String updateUserPage(@PathVariable Long id, Model model) {
        User user = userService.findByID(id);
        model.addAttribute("user", user);
        return "admin/updateUser";
    }

    @PostMapping("updateuser/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute User user, RedirectAttributes re) {
        if (user.getUserName() == "" || user.getEmail() == "" || user.getAddress() == "" || user.getPhone() == "") {
            re.addAttribute("nullValue", true);
            return "redirect:manager/updateuser/" + id;

        }
        if (!user.getPhone().matches("\\d{0,15}")) {
            re.addAttribute("phoneFormat", false);
            return "redirect:manager/updateuser/" + id;
        }

        User userUpdate = userService.findByID(id);
        if (userUpdate != null) {
            userUpdate.setRole(user.isRole());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setAddress(user.getAddress());
            userUpdate.setUserName(user.getUserName());
            userUpdate.setPhone(user.getPhone());
            userService.update(userUpdate);
        }
        return "redirect:/manager/user";
    }

    @GetMapping("adduser")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/adduser";
    }

    @PostMapping("adduser")
    public String addUser(@ModelAttribute User user, RedirectAttributes re) {
        if (user.getUserName() == "" || user.getEmail() == "" || user.getAddress() == "" || user.getPhone() == "") {
            re.addAttribute("nullValue", true);
            return "redirect:/manager/adduser";

        }
        if (!user.getPhone().matches("\\d{0,15}")) {
            re.addAttribute("phoneFormat", false);
            return "redirect:/manager/adduser";
        }
        user.setStatus(true);
        userService.save(user);
        return "redirect:/manager/user";
    }

    @GetMapping("removeruser/{id}")
    public String removerUser(@PathVariable Long id) {
        User user = userService.findByID(id);
        if (user != null) {
            user.setStatus(false);
            userService.update(user);
        }
        return "redirect:/manager/user";
    }

    @GetMapping("login")
    public String loginManager(Model model){
        model.addAttribute("user", new User());
        return "admin/loginManager";
    }
    @PostMapping("login")
    public String login(@ModelAttribute User user, HttpServletRequest request){
        User user1 = userRepository.loginUser(user.getEmail(),user.getPassword());
        if(user1!= null){
            request.getSession().setAttribute("user",user1);
        }
        return "redirect:/manager/product";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return  "redirect:/manager/login";
    }
}

