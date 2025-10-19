package com.example.payment_service.controller;

import com.example.payment_service.dto.PaymentRequestDTO;
import com.example.payment_service.dto.PaymentResponseDTO;
import com.example.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService)
    {
        this.paymentService=paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentRequestDTO paymentRequestDTO)
    {
        PaymentResponseDTO response=paymentService.processPayment(paymentRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO>getPaymentDetails(@PathVariable String orderId)
    {
        return ResponseEntity.ok(paymentService.getPaymentDetails(orderId));
    }
}
