package com.personal.product.repositories;

import com.personal.product.models.Product;

import java.util.List;

public interface CustomProductRepository {
    List<Product> findByNameContaining(String name);
    List<Product> findByPriceGreaterThan(Double price);
}
