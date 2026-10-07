package com.example.fruitmarket.entity.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CategorySalesVO {
    private String category;
    private BigDecimal qty;
    private BigDecimal amount;
}