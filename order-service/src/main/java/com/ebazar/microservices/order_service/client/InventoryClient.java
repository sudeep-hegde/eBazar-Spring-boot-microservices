package com.ebazar.microservices.order_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * REST CLIENT DOCS
 * docs.spring.io/spring-framework/reference/integration/rest-clients.html
 */
@Slf4j
//@FeignClient(name = "inventory", url = "${inventory.url}") //outdated
public interface InventoryClient {
    Logger log = LoggerFactory.getLogger(InventoryClient.class);

//    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory") as part of feign client

    @GetExchange("/api/inventory")
//    resilience4j.circuitbreaker.instances.inventory.* (same name as instances defined in application.yml)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode,@RequestParam Integer quantity);

    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }
}
