package com.ebazar.microservices.product.service.repository;

import com.ebazar.microservices.product.service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface extends
 * the MongoRepository interface provided by Spring Data MongoDB.
 * (JPARepository is used for JPA)
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}
