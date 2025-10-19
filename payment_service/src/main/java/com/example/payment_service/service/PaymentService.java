package com.example.payment_service.service;

import com.example.payment_service.dto.PaymentRequestDTO;
import com.example.payment_service.dto.PaymentResponseDTO;
import com.example.payment_service.dto.PaymentStatus;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private OrderClient orderClient;

    public PaymentService(PaymentRepository paymentRepository,OrderClient orderClient)
    {
        this.orderClient=orderClient;
        this.paymentRepository=paymentRepository;
    }

    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO)
    {
        String paymentId=generatePaymentId();
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setOrderId(paymentRequestDTO.getOrderId());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setCustomerId(paymentRequestDTO.getCustomerId());
        payment.setPaymentDate(LocalDateTime.now());
        boolean paymentSuccess=new Random().nextBoolean();

        if(paymentSuccess){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId(UUID.randomUUID().toString());
            orderClient.updateOrderStatus(paymentRequestDTO.getOrderId(),"CONFIRMED");
        }else{
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setTransactionId("N/A");
            orderClient.updateOrderStatus(paymentRequestDTO.getOrderId(),"CANCELLED");
        }

        paymentRepository.save(payment);

        PaymentResponseDTO paymentResponseDTO=new PaymentResponseDTO();
        paymentResponseDTO.setPaymentId(paymentId);
        paymentResponseDTO.setOrderId(payment.getOrderId());
        paymentResponseDTO.setAmount(payment.getAmount());
        paymentResponseDTO.setCustomerId(payment.getCustomerId());
        paymentResponseDTO.setPaymentDate(LocalDate.now());
        paymentResponseDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentResponseDTO.setTransactionId(payment.getTransactionId());
        return paymentResponseDTO;
    }
    private String generatePaymentId()
    {
        return "pay-" + UUID.randomUUID().toString().substring(0,8);
    }
    public PaymentResponseDTO getPaymentDetails(String orderId)
    {
        Payment payment=paymentRepository.findByOrderId(orderId);
        if(payment==null)
        {
            return null;
        }
        PaymentResponseDTO paymentResponseDTO=new PaymentResponseDTO();
        paymentResponseDTO.setPaymentId(payment.getPaymentId());
        paymentResponseDTO.setOrderId(payment.getOrderId());
        paymentResponseDTO.setAmount(payment.getAmount());
        paymentResponseDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentResponseDTO.setTransactionId(payment.getTransactionId());
        return paymentResponseDTO;

    }
}
