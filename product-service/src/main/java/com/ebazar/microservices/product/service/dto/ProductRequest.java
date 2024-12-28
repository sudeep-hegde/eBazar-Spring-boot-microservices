package com.ebazar.microservices.product.service.dto;

import java.math.BigDecimal;

/**
 * This class is used to represent request body.
 */
public record ProductRequest(String id, String name, String description, BigDecimal price) {
    /**
     * Immutable Data: Fields in a record are implicitly final and cannot be changed after the object is created.
     * Automatic Generation of Methods: Records automatically generate methods like equals(), hashCode(), and toString().
     */
}
