package com.ebazar.microservices.order_service.service;


import com.ebazar.microservices.order_service.client.InventoryClient;
import com.ebazar.microservices.order_service.dto.OrderRequest;
import com.ebazar.microservices.order_service.event.OrderPlacedEvent;
import com.ebazar.microservices.order_service.model.Order;
import com.ebazar.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    // arguments: topic name, value sending to the topic
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
            
            //send msg to Kafka Topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);

        } else {
            throw new RuntimeException("Product with skuCode: " + orderRequest.skuCode() + " is out of stock");
        }
    }

}
