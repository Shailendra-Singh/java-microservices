package me.shail.customer_service.service;

import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import me.shail.myboutique_commons.dto.CartDto;
import me.shail.myboutique_commons.dto.OrderDto;

@HttpExchange("/api/orders")
public interface OrderServiceClient {
    @PostExchange
    OrderDto create(CartDto cartDto);
}
