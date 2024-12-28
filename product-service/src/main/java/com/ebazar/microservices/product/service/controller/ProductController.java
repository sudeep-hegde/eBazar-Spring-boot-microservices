package com.ebazar.microservices.product.service.controller;

import com.ebazar.microservices.product.service.dto.ProductRequest;
import com.ebazar.microservices.product.service.dto.ProductResponse;
import com.ebazar.microservices.product.service.model.Product;
import com.ebazar.microservices.product.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //use this to return from this method with status code 201
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
