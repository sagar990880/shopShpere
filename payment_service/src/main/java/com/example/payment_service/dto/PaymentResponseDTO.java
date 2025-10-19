package com.example.payment_service.dto;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDTO {

    private String paymentId;
    private String orderId;
    private String customerId;
    private Double amount;
    private LocalDate paymentDate;
    private PaymentStatus paymentStatus;
    private String transactionId;
}
