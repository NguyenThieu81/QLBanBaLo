package com.example.QLBanBalo.controller;

import com.example.QLBanBalo.entity.Order;
import com.example.QLBanBalo.services.OrderServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderService;

    @GetMapping
    public String showAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order/add";
        }
        orderService.addOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editOrderForm(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "order/edit";
        }
        return "redirect:/orders";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateOrder(@PathVariable("id") Long id, @Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order/edit";
        }
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
