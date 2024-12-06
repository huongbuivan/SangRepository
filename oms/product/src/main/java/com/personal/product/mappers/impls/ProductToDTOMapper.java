package com.personal.product.mappers.impls;

import com.personal.product.dtos.ProductDTO;
import com.personal.product.mappers.ListMapper;
import com.personal.product.mappers.ToDTOMapper;
import com.personal.product.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductToDTOMapper implements ToDTOMapper<Product, ProductDTO>, ListMapper<Product, ProductDTO> {
    @Override
    public ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public List<ProductDTO> toDTOList(List<Product> products) {
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
