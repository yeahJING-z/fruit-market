package com.example.fruitmarket.entity.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PayMethodVO {
    private String payMethod;
    private Integer orderCount;
    private BigDecimal amount;
}