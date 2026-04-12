package me.shail.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.myboutique_commons.dto.PaymentDto;
import me.shail.order_service.model.Order;
import me.shail.order_service.model.Payment;
import me.shail.order_service.model.PaymentStatus;
import me.shail.order_service.repository.OrderRepository;
import me.shail.order_service.repository.PaymentRepository;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
@CircuitBreaker(name = "payment-service")
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public List<PaymentDto> findAll() {
        log.debug("Request to find all Payment");
        return this.paymentRepository.findAll().stream().map(PaymentService::mapToDto).toList();
    }

    @Transactional(readOnly = true)
    public PaymentDto findById(Long id) {
        log.debug("Request to find Payment: {}", id);
        return this.paymentRepository.findById(id).map(PaymentService::mapToDto).orElse(null);
    }

    public PaymentDto create(PaymentDto paymentDto) {
        log.debug("Request to create Payment: {}", paymentDto);
        Order order = this.orderRepository.findById(paymentDto.orderId())
                .orElseThrow(() -> new IllegalStateException("The Order does not exist"));

        return mapToDto(this.paymentRepository.save(
                new Payment(
                        paymentDto.paypalPaymentId(),
                        PaymentStatus.valueOf(paymentDto.status()),
                        order)));
    }

    public void delete(Long id) {
        log.debug("Request to delete Payment: {}", id);
        this.paymentRepository.deleteById(id);
    }

    public static PaymentDto mapToDto(Payment payment) {
        if (payment != null) {
            return new PaymentDto(
                    payment.getId(),
                    payment.getPaypalPaymentId(),
                    payment.getStatus().name(),
                    payment.getOrder().getId());
        }
        return null;
    }
}