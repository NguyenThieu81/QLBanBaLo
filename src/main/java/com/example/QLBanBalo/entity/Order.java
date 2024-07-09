package com.example.QLBanBalo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders") // "order" is a reserved word in SQL, so we name the table "orders"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @NotBlank(message = "Trạng thái đơn hàng không được để trống")
    @Column(name = "status", nullable = false)
    private String status;

    @Min(value = 0, message = "Tổng số tiền phải lớn hơn hoặc bằng 0")
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderItems;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToOne(mappedBy = "order")
    private Shipment shipment;

    // getters and setters
}
