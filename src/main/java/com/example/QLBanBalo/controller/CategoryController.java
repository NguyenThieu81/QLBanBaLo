package com.example.QLBanBalo.controller;

import com.example.QLBanBalo.entity.Category;
import com.example.QLBanBalo.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    @GetMapping
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category/add";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/edit";
        }
        return "redirect:/categories";
    }

    @PostMapping("/edit/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateCategory(@PathVariable("id") Long id, @Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category/edit";
        }
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
