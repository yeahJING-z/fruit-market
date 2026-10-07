package com.example.fruitmarket.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Integer productId;
    private String name;
    private String category;
    private BigDecimal price;
    private String unit;
    private Integer status;
    private LocalDateTime createdAt;

    // 关联库存字段（列表查询时带出来）
    private BigDecimal initStock;
    private BigDecimal currentStock;
    private BigDecimal warnThreshold;
}