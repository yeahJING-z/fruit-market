package com.example.fruitmarket.entity.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TopProductVO {
    private String name;
    private String unit;
    private BigDecimal qty;
    private BigDecimal amount;
}