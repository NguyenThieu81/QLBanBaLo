package com.example.QLBanBalo.controller;

import com.example.QLBanBalo.entity.Brand;
import com.example.QLBanBalo.services.BrandServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandServices brandService;

    @GetMapping
    public String showAllBrands(Model model) {
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "brand/list";
    }

    @GetMapping("/add")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addBrandForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "brand/add";
    }

    @PostMapping("/add")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addBrand(@Valid @ModelAttribute("brand") Brand brand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "brand/add";
        }
        brandService.addBrand(brand);
        return "redirect:/brands";
    }

    @GetMapping("/edit/{id}")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editBrandForm(@PathVariable("id") Long id, Model model) {
        Brand brand = brandService.getBrandById(id);
        if (brand != null) {
            model.addAttribute("brand", brand);
            return "brand/edit";
        }
        return "redirect:/brands";
    }

    @PostMapping("/edit/{id}")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateBrand(@PathVariable("id") Long id, @Valid @ModelAttribute("brand") Brand brand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "brand/edit";
        }
        brandService.updateBrand(brand);
        return "redirect:/brands";
    }

    @GetMapping("/delete/{id}")
  //  @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteBrand(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
        return "redirect:/brands";
    }
}
