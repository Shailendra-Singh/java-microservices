package me.shail.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.myboutique_commons.dto.OrderItemDto;
import me.shail.order_service.model.Order;
import me.shail.order_service.model.OrderItem;
import me.shail.order_service.repository.OrderItemRepository;
import me.shail.order_service.repository.OrderRepository;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
@CircuitBreaker(name = "orderitem-service-resilience")
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public List<OrderItemDto> findAll() {
        log.debug("Request to get all OrderItems");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemService::mapToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderItemDto findById(Long id) {
        log.debug("Request to get OrderItem: {}", id);
        return this.orderItemRepository.findById(id).map(OrderItemService::mapToDto).orElse(null);
    }

    public OrderItemDto create(OrderItemDto orderItemDto) {
        log.debug("Request to create OrderItemDto: {}", orderItemDto);
        Order order = this.orderRepository.findById(orderItemDto.orderId())
                .orElseThrow(() -> new IllegalStateException("The Order does not exist"));

        return mapToDto(this.orderItemRepository.save(
                new OrderItem(
                        orderItemDto.quantity(),
                        orderItemDto.productId(),
                        order)));
    }

    public void delete(Long id) {
        log.debug("Request to delete OrderItem: {}", id);
        this.orderItemRepository.deleteById(id);
    }

    public static OrderItemDto mapToDto(OrderItem orderItem) {
        if (orderItem != null) {
            return new OrderItemDto(
                    orderItem.getId(),
                    orderItem.getQuantity(),
                    orderItem.getProductId(),
                    orderItem.getOrder().getId());
        }
        return null;
    }
}
