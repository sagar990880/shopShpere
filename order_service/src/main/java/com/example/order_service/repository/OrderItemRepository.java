package com.example.order_service.repository;

import com.example.order_service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
    List<OrderItem>findByOrderId(String orderId);
}
