package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên thương hiệu không được để trống")
    @Size(max = 50, message = "Tên thương hiệu phải ít hơn 50 ký tự")
    @Column(name = "name")
    private String name;
    private String description;


    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
