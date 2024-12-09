package com.personal.product.services;

import com.personal.product.dtos.ProductDTO;
import com.personal.product.models.Product;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(Product product);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<ProductDTO> searchProductsByName(String name);
    List<ProductDTO> getProductsByPrice(Double price);
}
