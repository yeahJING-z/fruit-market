package com.example.fruitmarket.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PointsRecord {
    private Integer recordId;
    private Integer memberId;
    private Integer changePoints;
    private String reason;
    private Integer orderId;
    private LocalDateTime createdAt;
}