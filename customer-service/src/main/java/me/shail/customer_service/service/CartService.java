package me.shail.customer_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.customer_service.model.Cart;
import me.shail.customer_service.model.CartStatus;
import me.shail.customer_service.model.Customer;
import me.shail.customer_service.repository.CartRepository;
import me.shail.customer_service.repository.CustomerRepository;
import me.shail.myboutique_commons.dto.CartDto;
import me.shail.myboutique_commons.dto.OrderDto;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
@CircuitBreaker(name = "cart-service")
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final OrderServiceClient orderServiceClient;

    public List<CartDto> findAll() {
        log.debug("Request to get all Carts");
        return this.cartRepository.findAll()
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CartDto> findAllActiveCarts() {
        return this.cartRepository.findByStatus(CartStatus.NEW)
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    public CartDto create(Long customerId) {
        if (this.getActiveCart(customerId) == null) {
            Customer customer = this.customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalStateException("Customer doesn't exist!"));

            Cart cart = new Cart(null, customer, CartStatus.NEW);
            OrderDto order = this.orderServiceClient.create(mapToDto(cart));
            cart.setOrderId(order.id());

            return mapToDto(this.cartRepository.save(cart));
        } else {
            throw new IllegalStateException("There is already an active cart");
        }
    }

    @Transactional(readOnly = true)
    public CartDto findById(Long id) {
        log.debug("Request to get Cart: {}", id);
        return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
    }

    public void delete(Long id) {
        log.debug("Request to delete Cart: {}", id);
        Cart cart = this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find cart with id: " + id));
        cart.setStatus(CartStatus.CANCELLED);
        this.cartRepository.save(cart);
    }

    public CartDto getActiveCart(Long customerId) {
        List<Cart> carts = this.cartRepository
                .findByStatusAndCustomerId(CartStatus.NEW, customerId);
        if (carts != null) {
            if (carts.size() == 1) {
                return mapToDto(carts.get(0));
            }
            if (carts.size() > 1) {
                throw new IllegalStateException("Many active carts detected");
            }
        }

        return null;
    }

    public static CartDto mapToDto(Cart cart) {
        if (cart != null) {
            return new CartDto(cart.getId(),
                    cart.getOrderId(),
                    CustomerService.mapToDto(cart.getCustomer()),
                    cart.getStatus().name());
        }
        return null;
    }
}
