package com.example.controller;

import com.example.model.Product;
import com.example.resource.SaveProductResource;
import com.example.resource.ProductResource;
import com.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ProductResource getProductById(@PathVariable(name = "productId") Long productId){
        return convertToResource(productService.getProductById(productId));
    }

    @GetMapping("/arriving")
    public Page<ProductResource> getArrivingProducts(Pageable pageable){
        Page<Product> productPage = productService.getAllArrivingProducts(pageable);
        List<ProductResource> resources = productPage.getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/exiting")
    public Page<ProductResource> getExitingProducts(Pageable pageable){
        Page<Product> productPage = productService.getAllExitingProducts(pageable);
        List<ProductResource> resources = productPage.getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Product convertToEntity(SaveProductResource resource){
        return mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity){
        return mapper.map(entity, ProductResource.class);
    }
}
