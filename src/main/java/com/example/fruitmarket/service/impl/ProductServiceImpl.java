package com.example.fruitmarket.service.impl;

import com.example.fruitmarket.common.BizException;
import com.example.fruitmarket.entity.Product;
import com.example.fruitmarket.mapper.ProductMapper;
import com.example.fruitmarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<Product> list(String category) {
        return productMapper.list(category);
    }

    @Override
    public Product getById(Integer id) {
        Product p = productMapper.getById(id);
        if (p == null) throw new BizException("商品不存在");
        return p;
    }

    @Override
    public Integer create(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new BizException("商品名称不能为空");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new BizException("价格必须为非负数");
        }
        productMapper.insert(product);
        return product.getProductId();
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(Integer id) {
        int used = productMapper.countOrderItemByProductId(id);
        if (used > 0) {
            throw new BizException("该商品已产生历史订单，禁止删除");
        }
        productMapper.deleteById(id);
    }
}