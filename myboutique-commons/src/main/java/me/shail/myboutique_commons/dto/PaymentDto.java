package me.shail.myboutique_commons.dto;

public record PaymentDto(
        Long id,
        String paypalPaymentId,
        String status,
        Long orderId) {
}
