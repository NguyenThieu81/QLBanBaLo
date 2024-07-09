package com.example.QLBanBalo.services;

import com.example.QLBanBalo.entity.Cart;
import com.example.QLBanBalo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }
}
