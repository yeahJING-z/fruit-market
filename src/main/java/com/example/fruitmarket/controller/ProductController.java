package com.example.fruitmarket.controller;

import com.example.fruitmarket.common.Result;
import com.example.fruitmarket.entity.Product;
import com.example.fruitmarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Result<List<Product>> list(@RequestParam(required = false) String category) {
        return Result.ok(productService.list(category));
    }

    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable Integer id) {
        return Result.ok(productService.getById(id));
    }

    @PostMapping
    public Result<Integer> create(@RequestBody Product product) {
        return Result.ok(productService.create(product));
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Integer id, @RequestBody Product product) {
        product.setProductId(id);
        productService.update(product);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return Result.ok();
    }
}