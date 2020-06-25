package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.*;
import com.assj5.thien.assj5.repository.ProductDetailRepository;
import com.assj5.thien.assj5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BillController {

    @Autowired
    private ProductSevice productSevice;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;
    @Autowired
    private BillDetailSevice billDetailSevice;

    @GetMapping("/cart")
    public String cart9(Model model,HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute("cart");
        model.addAttribute("category",categoryService.findAll());
        model.addAttribute("cart",carts);
        return "cart";
    }

    @PostMapping("/cart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @RequestParam("total") int total, @RequestParam("size") String size, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Cart> cartItems = new ArrayList<>();
            List<ProductDetail> productDetails = productDetailRepository.findAllByProductId(productId);
            if (productDetails != null) {
                for (ProductDetail productDetail : productDetails) {
                    if (productDetail.getSizes().getSizeName().equals(size)) {
                        cartItems.add(new Cart(productSevice.findByID(productId), total, productDetail.getSizes(), productDetail.getProductAmount()));
                    }
                }
            }
            session.setAttribute("cart", cartItems);
            session.setAttribute("myCartTotal", totalPriceItems(cartItems));
            session.setAttribute("myCartNum", cartItems.size());
        } else {
            List<Cart> carts = (List<Cart>) session.getAttribute("cart");
            int index = this.existsItems(productId, carts,size);
            if (index == -1) {
                addToCart(productId, size, total, session);
            } else {
                int quantity = carts.get(index).getQuantity() + total ;
                carts.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", carts);
            session.setAttribute("myCartTotal", totalPriceItems(carts));
            session.setAttribute("myCartNum", carts.size());
        }
        return "redirect:/cart";

    }

    @GetMapping("/remove/{productId}")
    public String removeCart(@PathVariable Long productId, HttpSession session ){
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cart");
            int index = this.existsItems(productId, cartItems);
            cartItems.remove(index);
            if(cartItems.size()==0){
                session.setAttribute("cart", null);
            }
            else {
                session.setAttribute("cart", cartItems);
            }

        session.setAttribute("myCartTotal", totalPriceItems(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/cart";
    }

    @GetMapping("/update/{productId}")
    public String updateCart(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity, Model model, HttpSession session){
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cart");
            int index = this.existsItems(productId,cartItems);
            cartItems.get(index).setQuantity(quantity);

        session.setAttribute("cart",cartItems);
        session.setAttribute("myCartTotal",totalPriceItems(cartItems));
        session.setAttribute("myCartNum",cartItems.size());
        return  "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkOut( Model model, HttpSession session) {
        if(session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            model.addAttribute("user",userService.findByID(user.getUserId()));
        }
        model.addAttribute("bill",new Bill());
      return "checkout";
    }

    @PostMapping("/checkout")
    public String checkOut(@ModelAttribute Bill bill,@ModelAttribute User user,HttpSession session){
        String billCode = randomAlphaNumeric(5);
        List<Cart> billDetails = (List<Cart>) session.getAttribute("cart");
        bill.setBillDate(new Timestamp(new Date().getTime()));
        bill.setBillCode(billCode);
        if(user!=null){
            bill.setEmail(user.getEmail());
            bill.setCustomerPhone(user.getPhone());
            bill.setCustomerName(user.getUserName());
            bill.setAddress(user.getAddress());
            User userSave = userService.findByEmail(user.getEmail());
            bill.setUser(userSave);
        }
        billService.saveBySession(bill);
        List<BillDetail> billDetailList = new ArrayList<>();
        for(Cart billDetail : billDetails){
            Product product = productSevice.findByID(billDetail.getProduct().getProductId());
            double price = product.getProductPrice() - ((product.getProductPrice()/100) * product.getProductSale());
            billDetailList.add(new BillDetail(bill,billDetail.getProduct(),billDetail.getQuantity(),price,billDetail.getSize()));

        }
        billDetailSevice.saveAll(billDetailList);
        session.removeAttribute("cart");
        session.setAttribute("myCartTotal",0);
        session.setAttribute("myCartNum",0);
        return "redirect:/cart";
    }

    public double totalPriceItems(List<Cart> cartItems) {
        int count = 0;
        for (Cart list : cartItems) {
            count += (list.getProduct().getProductPrice() - (list.getProduct().getProductPrice() / 100 * list.getProduct().getProductSale())) * list.getQuantity();
        }
        return count;
    }

    public int existsItems(Long produtId, List<Cart> carts,String size) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProduct().getProductId().equals(produtId) && carts.get(i).getSize().getSizeName().equals(size)) {
                return i;
            }
        }
        return -1;
    }

    public int existsItems(Long produtId, List<Cart> carts) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProduct().getProductId().equals(produtId)) {
                return i;
            }
        }
        return -1;
    }

    public List<Cart> addToCart(Long productId, String size, int total, HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute("cart");
        List<ProductDetail> productDetails = productDetailRepository.findAllByProductId(productId);
        if (productDetails != null) {
            for (ProductDetail productDetail : productDetails) {
                if (productDetail.getSizes().getSizeName().equals(size)) {
                    carts.add(new Cart(productSevice.findByID(productId), total, productDetail.getSizes(), productDetail.getProductAmount()));
                }
            }
        }
        return carts;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
