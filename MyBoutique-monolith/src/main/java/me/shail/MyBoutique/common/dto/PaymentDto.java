package me.shail.MyBoutique.common.dto;

public record PaymentDto(
                Long id,
                String paypalPaymentId,
                String status,
                Long orderId) {
}
