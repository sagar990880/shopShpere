package com.example.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class OrderItem {
    @Id
    private String orderItemId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
}
