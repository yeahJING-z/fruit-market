package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;

@Mapper
public interface InventoryMapper {
    Product selectProductForUpdate(@Param("productId") Integer productId);  // ★ 返回 Product
    int decreaseStock(@Param("productId") Integer productId,
                      @Param("qty") BigDecimal qty);
}