package com.example.fruitmarket.entity.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WarnItemVO {
    private String name;
    private String unit;
    private BigDecimal currentStock;
    private BigDecimal warnThreshold;
    private BigDecimal gap;
}