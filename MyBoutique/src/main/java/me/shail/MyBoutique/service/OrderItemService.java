package me.shail.MyBoutique.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.MyBoutique.common.dto.OrderItemDto;
import me.shail.MyBoutique.model.Order;
import me.shail.MyBoutique.model.OrderItem;
import me.shail.MyBoutique.model.Product;
import me.shail.MyBoutique.repository.OrderItemRepository;
import me.shail.MyBoutique.repository.OrderRepository;
import me.shail.MyBoutique.repository.ProductRepository;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

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

        Product product = this.productRepository.findById(orderItemDto.productId())
                .orElseThrow(() -> new IllegalStateException("The Product does not exist"));

        return mapToDto(this.orderItemRepository.save(
                new OrderItem(
                        orderItemDto.quantity(),
                        product,
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
                    orderItem.getProduct().getId(),
                    orderItem.getOrder().getId());
        }
        return null;
    }
}
