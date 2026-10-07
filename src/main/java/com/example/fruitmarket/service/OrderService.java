package com.example.fruitmarket.service;

import com.example.fruitmarket.entity.Order;
import com.example.fruitmarket.entity.dto.OrderCreateDTO;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreateDTO dto);
    List<Order> list();
}