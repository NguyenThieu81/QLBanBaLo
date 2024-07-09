package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime shipmentDate;
    private String status;
    private String trackingNumber;

    @OneToOne
    private Order order;

    // getters and setters
}
