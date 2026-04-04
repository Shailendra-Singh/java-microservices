package me.shail.MyBoutique.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.MyBoutique.dto.PaymentDto;
import me.shail.MyBoutique.model.Order;
import me.shail.MyBoutique.model.Payment;
import me.shail.MyBoutique.model.PaymentStatus;
import me.shail.MyBoutique.repository.OrderRepository;
import me.shail.MyBoutique.repository.PaymentRepository;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
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
        Order order = this.orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new IllegalStateException("The Order does not exist"));

        return mapToDto(this.paymentRepository.save(
                new Payment(
                        paymentDto.getPaypalPaymentId(),
                        PaymentStatus.valueOf(paymentDto.getStatus()),
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