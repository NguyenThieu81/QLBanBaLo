package com.example.QLBanBalo.controller;

import com.example.QLBanBalo.entity.Product;
import com.example.QLBanBalo.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productServices.getAllProducts();
        model.addAttribute("products", products);
        return "home/index";
    }
}
