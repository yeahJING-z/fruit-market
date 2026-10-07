package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderItemMapper {
    int insert(OrderItem item);
    List<OrderItem> listByOrderId(@Param("orderId") Integer orderId);
}