package com.example.fruitmarket.entity.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateDTO {
    private Integer memberId;
    private String payMethod;
    private List<Item> items;

    @Data
    public static class Item {
        private Integer productId;
        private BigDecimal quantity;
    }
}