package com.ebazar.microservices.product.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
/**
 * This class is used to represent the product entity in the database.
 */
@Document(value = "product") // This annotation is used to map the object to the collection in the MongoDB database.
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
