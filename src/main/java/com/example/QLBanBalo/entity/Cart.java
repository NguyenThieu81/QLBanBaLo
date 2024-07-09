package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    // getters and setters
}