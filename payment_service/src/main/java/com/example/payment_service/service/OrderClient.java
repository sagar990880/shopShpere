package com.example.payment_service.service;

import com.example.payment_service.dto.OrderStatusUpdateRequestDTO;
import com.example.payment_service.dto.PaymentRequestDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {
    private final RestTemplate restTemplate;
    public OrderClient(RestTemplateBuilder builder)
    {
        this.restTemplate=builder.build();
    }

    public void updateOrderStatus(String orderId,String status)
    {
        //http://localhost:6001/orders/ord-5b4b0d3d/status?status=CONFIRMED
        String url="http://localhost:6001/orders/"+orderId + "/status?status="+status;
         OrderStatusUpdateRequestDTO request=new OrderStatusUpdateRequestDTO(orderId,status);
        String response=restTemplate.patchForObject(url,request,String.class);
        System.out.println("Order status updated:"+response);
    }

}
