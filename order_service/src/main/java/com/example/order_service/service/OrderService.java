package com.example.order_service.service;

import com.example.order_service.dto.*;
import com.example.order_service.entity.Orders;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderItemRepository;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ProductClient productClient;

    public OrderService(OrderRepository orderRepository,OrderItemRepository orderItemRepository,ProductClient productClient)
    {
        this.orderItemRepository=orderItemRepository;
        this.orderRepository=orderRepository;
        this.productClient=productClient;
    }

    public OrderResponseDTO placeOrder(OrderRequestDTO requestDTO)
    {
        //Generate OrderId
        String orderId=generateOrderID();
        double totalAmount=0.0;

        List<OrderItem> orderItems=new ArrayList<>();



        for(OrderItemRequestDTO itemRequest : requestDTO.getItems())
        {
            ProductResponseDTO product=productClient.getProductName(itemRequest.getProductId());
            System.out.println("Check->-->-"+product.getPrice());
            if(product.getStockQuantity()<itemRequest.getQuantity()){
                throw new RuntimeException("Insufficient Stock for Product"+ product.getName());
            }
            productClient.updateStock(itemRequest.getProductId(),-itemRequest.getQuantity());
            double itemTotal=itemRequest.getQuantity()*product.getPrice();
            totalAmount+=itemTotal;

            OrderItem orderItem=new OrderItem(generateOrderItemId(),orderId,itemRequest.getProductId()
                    ,itemRequest.getQuantity(),product.getPrice());

            orderItems.add(orderItem);
        }
        Orders orders =new Orders(orderId, requestDTO.getCustomerId(),
                LocalDateTime.now(),totalAmount, OrderStatus.PENDING);
        orderRepository.save(orders);
        orderItemRepository.saveAll(orderItems);

       return new OrderResponseDTO(orders.getOrderId(), orders.getCustomerId(), orders.getOrderDate(), orders.getTotalAmount(), orders.getStatus(),orderItems);
    }

    public OrderResponseDTO getOrderById(String orderId){
        Orders orders =orderRepository.findById(orderId)
                .orElseThrow(()->new RuntimeException("order not found with Id :"+orderId));
        List<OrderItem>items=orderItemRepository.findByOrderId(orderId);
        return new OrderResponseDTO(orders.getOrderId(), orders.getCustomerId(), orders.getOrderDate(), orders.getTotalAmount(), orders.getStatus(),items);
    }

    public  List<OrderResponseDTO> getOrderByCustomerId(String customerId){
        List<Orders> orders=orderRepository.findByCustomerId(customerId);
        List<OrderResponseDTO> responseList = new ArrayList<>();

        for (Orders order:orders)
        {
            List<OrderItem> items=orderItemRepository.findByOrderId(order.getOrderId());
            responseList.add(new OrderResponseDTO(order.getOrderId(),order.getCustomerId(),order.getOrderDate(),order.getTotalAmount(),order.getStatus(),items));
        }
        return responseList;
    }

    public void updateOrderStatus(String orderId,OrderStatus orderStatus)
    {
        Orders orders =orderRepository.findById(orderId)
                .orElseThrow(()->new RuntimeException("Order not found with ID:"+orderId));

        orders.setStatus(orderStatus);
        orderRepository.save(orders);
    }

    private String generateOrderID()
    {
        return "ord-"+ UUID.randomUUID().toString().substring(0,8);
    }

    private String generateOrderItemId()
    {
        return "item-"+ UUID.randomUUID().toString().substring(0,8);
    }

}
