package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> list(@Param("category") String category);

    Product getById(@Param("id") Integer id);

    int insert(Product product);

    int update(Product product);

    int deleteById(@Param("id") Integer id);

    int countOrderItemByProductId(@Param("id") Integer id);
}