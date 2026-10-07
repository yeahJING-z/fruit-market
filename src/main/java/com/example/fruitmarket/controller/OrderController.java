package com.example.fruitmarket.controller;

import com.example.fruitmarket.common.Result;
import com.example.fruitmarket.entity.Order;
import com.example.fruitmarket.entity.dto.OrderCreateDTO;
import com.example.fruitmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    /** 下单 */
    @PostMapping
    public Result<Order> create(@RequestBody OrderCreateDTO dto) {
        return Result.ok(orderService.createOrder(dto));
    }

    /** 订单列表 */
    @GetMapping
    public Result<List<Order>> list() {
        return Result.ok(orderService.list());
    }
}