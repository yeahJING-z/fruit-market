package com.example.fruitmarket.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer orderId;
    private String orderNo;
    private Integer memberId;
    private Integer staffId;
    private BigDecimal totalAmount;
    private String payMethod;
    private String status;
    private LocalDateTime createdAt;
}