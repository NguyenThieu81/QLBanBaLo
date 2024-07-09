package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime paymentDate;
    private Double amount;
    private String paymentMethod;

    @OneToOne
    private Order order;

    // getters and setters
}