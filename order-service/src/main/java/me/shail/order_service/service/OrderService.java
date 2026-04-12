package me.shail.order_service.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.myboutique_commons.dto.OrderDto;
import me.shail.order_service.model.Order;
import me.shail.order_service.model.OrderStatus;
import me.shail.order_service.repository.OrderRepository;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
@CircuitBreaker(name = "order-service-resilience")
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> findAll() {
        log.debug("Request to find all Orders");
        return this.orderRepository.findAll().stream().map(OrderService::mapToDto).toList();
    }

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        log.debug("Request to find Order: {}", id);
        return this.orderRepository.findById(id).map(OrderService::mapToDto).orElse(null);
    }

    // public List<OrderDto> findAllByUser(Long id) {
    // return this.orderRepository.findByCartIdCustomer_Id(id)
    // .stream()
    // .map(OrderService::mapToDto)
    // .toList();
    // }

    public OrderDto create(OrderDto orderDto) {
        log.debug("Request to create Order: {}", orderDto);
        return mapToDto(this.orderRepository.save(
                new Order(
                        BigDecimal.ZERO,
                        OrderStatus.CREATION,
                        null,
                        null,
                        null,
                        Collections.emptySet(),
                        null)));
    }

    public OrderDto create(Long cartId) {
        log.debug("Request to create Order with a Cart: {}", cartId);

        return mapToDto(this.orderRepository.save(
                new Order(
                        BigDecimal.ZERO,
                        OrderStatus.CREATION,
                        null,
                        null,
                        null,
                        Collections.emptySet(),
                        cartId)));
    }

    public void delete(Long id) {
        log.debug("Request to delete Order: {}", id);
        this.orderRepository.deleteById(id);
    }

    public static OrderDto mapToDto(Order order) {
        if (order != null) {
            return new OrderDto(
                    order.getId(),
                    order.getTotalPrice(),
                    order.getStatus().name(),
                    order.getShipped(),
                    PaymentService.mapToDto(order.getPayment()),
                    AddressService.mapToDto(order.getShipmentAddress()),
                    order.getOrderItems().stream().map(OrderItemService::mapToDto).collect(Collectors.toSet()));
        }
        return null;
    }

}
