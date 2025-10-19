package com.example.order_service.dto;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private String productId;
    private int quantity;
}
