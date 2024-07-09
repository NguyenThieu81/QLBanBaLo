package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm phải ít hơn 100 ký tự")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 100, message = "Mô tả phải ít hơn 100 ký tự")
    @Column(name = "description")
    private String description;
    @Min(value = 150000, message = "Giá phải lớn hơn hoặc bằng 150000")
    @Column(name = "price")
    private Double price;
    @Min(value = 1)
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String ImagePath;
}
