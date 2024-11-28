package com.personal.product.services.impls;

import com.personal.product.dtos.ProductDTO;
import com.personal.product.mappers.ListMapper;
import com.personal.product.mappers.ToDTOMapper;
import com.personal.product.models.Product;
import com.personal.product.repositories.ProductRepository;
import com.personal.product.services.EntityProductService;
import com.personal.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService, EntityProductService {
    private final ProductRepository productRepository;
    private final ToDTOMapper<Product, ProductDTO> productToDTOMapper;
    private final ListMapper<Product, ProductDTO> ProductListMapper;

    @Override
    public ProductDTO createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return productToDTOMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productToDTOMapper.toDTO(getEntityProductById(id));
    }

    @Override
    public Product getEntityProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductListMapper.toDTOList(products);
    }

    @Override
    public ProductDTO updateProduct(Long id, Product product) {
        Product existingProduct = getEntityProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        return productToDTOMapper.toDTO(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        return ProductListMapper.toDTOList(products);
    }

    public List<ProductDTO> getProductsByPrice(Double price) {
        List<Product> products = productRepository.findByPriceGreaterThan(price);
        return ProductListMapper.toDTOList(products);
    }
}

