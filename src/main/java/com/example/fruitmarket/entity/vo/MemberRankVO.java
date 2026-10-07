package com.example.fruitmarket.entity.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MemberRankVO {
    private Integer memberId;
    private String name;
    private String phone;
    private Integer pointsBalance;
    private Integer orderCount;
    private BigDecimal totalAmount;
}