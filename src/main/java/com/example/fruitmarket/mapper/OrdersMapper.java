package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper {
    int insert(Order order);
    List<Order> list();
    Order getById(@Param("id") Integer id);
    List<Order> listByMember(@Param("memberId") Integer memberId);   // ★ 新增
}