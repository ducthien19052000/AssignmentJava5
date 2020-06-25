package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Cart;
import com.assj5.thien.assj5.model.User;
import com.assj5.thien.assj5.repository.BillRepository;
import com.assj5.thien.assj5.repository.UserRepository;
import com.assj5.thien.assj5.service.BillService;
import com.assj5.thien.assj5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpSession session, RedirectAttributes re){
        // check dang ky tai khoan
        if (user.getEmail() == "" || user.getAddress() == "" || user.getPassword() == "" || user.getUserName() == "" || user.getPhone() == "") {
            re.addAttribute("nullValue", true);
            return "redirect:/register";
        }
        if (!user.getEmail().matches("\\w+@+\\w+\\.+(\\w|\\w+\\.+\\w){1,300}")) {
            re.addAttribute("emailFormat", false);
            return "redirect:/register";
        }
        if (!user.getPhone().matches("\\d{0,15}")) {
            re.addAttribute("phoneFormat", false);
            return "redirect:/register";
        }
        user.setStatus(true);
        user.setRole(false);
        session.setAttribute("userRegister",user);
        String userCode = randomAlphaNumeric(7);
        session.setAttribute("registerCode",userCode);

        // Create a Simple MailMessage.
        String from = "thienndph07761@fpt.edu.vn";
        String nameFrom = "SkyTheme";
        String to = user.getEmail();
        String subject = "Đăng kí tài khoản " + user.getUserName();
        String body = "Chào bạn " + user.getUserName() + ". Mã xác nhận đăng kí Sky của bạn là : " + " " + userCode + " " +
                " Vui lòng không chia sẻ mã này với người khác.";
        sendEmail(from, nameFrom, to, from, subject, body);

        int maxInactiveInterval = session.getMaxInactiveInterval();
        int currentInactiveTime = (int) (System.currentTimeMillis() - session.getLastAccessedTime() / 1000);
        session.setMaxInactiveInterval(maxInactiveInterval + currentInactiveTime);

        return "check-register";
    }

    @PostMapping("/verifyAccount")
    public String verifyAccount( RedirectAttributes re,@RequestParam String checkRegister,HttpSession session){
        String registerCode = (String) session.getAttribute("registerCode");
        if(!registerCode.equals(checkRegister)){
            re.addAttribute("checkCodeFormat",true);
            return "check-register";
        }
        User userSe = (User) session.getAttribute("userRegister");
        userRepository.save(userSe);
        session.removeAttribute("registerCode");
        session.removeAttribute("userRegister");
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("user", new User());
        return "login-user";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,RedirectAttributes re, HttpSession session){
        User userLogin = userRepository.loginUser(user.getEmail(),user.getPassword());
        if(userLogin != null){
            session.setAttribute("user",userLogin);
        }
        else {
            re.addAttribute("login",false);
            return "redirect:/login";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        session.removeAttribute("user");
        session.setAttribute("cart", null);
        session.setAttribute("myCartTotal", null);
        session.setAttribute("myCartNum", null);
        return "redirect:/";
    }
    @GetMapping("/info")
    public String infoUserpage(Model model){
        model.addAttribute("category",categoryService.findAll());
        return "user-info";
    }
//    @GetMapping("receipt-account/{memberId}")
//    public String viewReceipt(@PathVariable("memberId") Long id, Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("category", categoryService.findAll());
//        model.addAttribute("receiptAccount", billRepository.fillAllReceiptByMemberId(id));
//        return "receipt-account";
//    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    private int existsMember(Long productId, List<Cart> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }
    public double totalPriceMember(List<Cart> cartItems) {
        int count = 0;
        for (Cart list : cartItems) {
            count += (list.getProduct().getProductPrice() - (list.getProduct().getProductPrice() / 100 * list.getProduct().getProductSale())) * list.getQuantity();
        }
        return count;
    }

    public void sendEmail(String from, String nameFrom, String to, String replyTo, String subject, String body) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from, nameFrom);
            helper.setTo(to);
            helper.setReplyTo(replyTo, nameFrom);
            helper.setSubject(subject);
            helper.setText(body);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
