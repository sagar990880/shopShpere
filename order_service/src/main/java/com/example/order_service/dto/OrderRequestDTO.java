package com.example.order_service.dto;

import com.example.order_service.entity.OrderItem;
import jakarta.persistence.PrePersist;
import java.util.ArrayList;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private String customerId;
    private List<OrderItemRequestDTO> items = new ArrayList<>();
    //private List<OrderItemRequestDTO>item;
}
