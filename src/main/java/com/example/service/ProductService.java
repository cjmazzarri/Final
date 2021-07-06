package com.example.service;

import com.example.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAllArrivingProducts(Pageable pageable);
    Page<Product> getAllExitingProducts(Pageable pageable);
    Product getProductById(Long id);
}
