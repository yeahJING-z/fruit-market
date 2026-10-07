package com.example.fruitmarket.service;

import com.example.fruitmarket.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> list(String category);
    Product getById(Integer id);
    Integer create(Product product);
    void update(Product product);
    void delete(Integer id);
}