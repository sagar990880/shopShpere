package com.example.payment_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequestDTO {
    private String oderId;
    private String status;
}
