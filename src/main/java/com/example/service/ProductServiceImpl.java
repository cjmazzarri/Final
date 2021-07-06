package com.example.service;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllArrivingProducts(Pageable pageable) {
        return productRepository.findAllByAction("LLEGADA", pageable);
    }

    @Override
    public Page<Product> getAllExitingProducts(Pageable pageable) {
        return productRepository.findAllByAction("SALIDA", pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "Id", id)
        );
    }
}
