package com.ebazar.microservices.product.service.service;

import com.ebazar.microservices.product.service.dto.ProductRequest;
import com.ebazar.microservices.product.service.dto.ProductResponse;
import com.ebazar.microservices.product.service.model.Product;
import com.ebazar.microservices.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Product.builder() (lomboks builder helps to create an object of Product class with the help of builder pattern)
     * record procutRequest (Java 14 feature, used to create immutable objects) (prodcutRequest.name() instead of getters)
     * @param productRequest
     */
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully.");
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                )).toList();
    }
}
