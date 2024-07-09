package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Cart cart;

    // getters and setters
}
