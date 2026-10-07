package com.example.fruitmarket.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Member {
    private Integer memberId;
    private String name;
    private String phone;
    private String passwordHash;
    private Integer pointsBalance;
    private String level;
    private LocalDateTime createdAt;
}