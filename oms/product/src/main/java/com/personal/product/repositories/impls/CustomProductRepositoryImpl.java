package com.personal.product.repositories.impls;

import com.personal.product.models.Product;
import com.personal.product.repositories.CustomProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByNameContaining(String name) {
        String jpql = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:name)";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", "%" + name.toLowerCase() + "%");
        return query.getResultList();
    }

    @Override
    public List<Product> findByPriceGreaterThan(Double price) {
        String jpql = "SELECT p FROM Product p WHERE p.price > :price";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("price", price);
        return query.getResultList();
    }
}
