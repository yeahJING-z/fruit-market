package com.example.fruitmarket.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockLog {
    private Integer logId;
    private Integer productId;
    private String changeType;
    private BigDecimal changeQty;
    private BigDecimal stockAfter;
    private Integer refOrderId;
    private LocalDateTime createdAt;
}