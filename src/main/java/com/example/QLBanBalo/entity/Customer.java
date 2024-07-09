package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(max = 100, message = "Tên khách hàng phải ít hơn 100 ký tự")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
// getters and setters

