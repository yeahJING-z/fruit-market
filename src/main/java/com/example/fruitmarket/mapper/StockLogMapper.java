package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.StockLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockLogMapper {
    int insert(StockLog log);
}