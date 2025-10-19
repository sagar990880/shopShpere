package com.example.order_service.dto;

import com.example.order_service.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItem> items;


    // Custom getter
    public Object getTotalAmount() {
        if (status != null && status == OrderStatus.CONFIRMED) {
            return "PAID";
        }
        return totalAmount;
    }
}
