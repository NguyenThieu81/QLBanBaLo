package com.example.QLBanBalo.controller;

import com.example.QLBanBalo.entity.Product;
import com.example.QLBanBalo.services.ProductServices;
import com.example.QLBanBalo.services.CategoryServices;
import com.example.QLBanBalo.services.BrandServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
@RequestMapping("/products")
public class ProductController {
    private static final String UPLOADED_DIR = "src/main/resources/static/image/";
    @Autowired
    private ProductServices productServices;

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private BrandServices brandServices;

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productServices.getAllProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/add")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryServices.getAllCategories());
        model.addAttribute("brands", brandServices.getAllBrands());
        return "product/add";
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addProduct(@Valid @ModelAttribute("product") Product product,@RequestParam("image") MultipartFile file, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryServices.getAllCategories());
            model.addAttribute("brands", brandServices.getAllBrands());
            return "product/add";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_DIR+file.getOriginalFilename());
            Files.write(path,bytes);
            product.setImagePath("/image/" + file.getOriginalFilename());
        }catch (IOException e){
            e.printStackTrace();
        }
        productServices.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productServices.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryServices.getAllCategories());
            model.addAttribute("brands", brandServices.getAllBrands());
            return "product/edit";
        }

        return "redirect:/products";
    }

    @PostMapping("/edit/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute("product") Product product,@RequestParam("image") MultipartFile file, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryServices.getAllCategories());
            model.addAttribute("brands", brandServices.getAllBrands());
            return "product/edit";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_DIR+file.getOriginalFilename());
            Files.write(path,bytes);
            product.setImagePath("/image/" + file.getOriginalFilename());
        }catch (IOException e){
            e.printStackTrace();
        }
        productServices.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteProduct(@PathVariable("id") Long id) {
        productServices.deleteProduct(id);
        return "redirect:/products";
    }
}
