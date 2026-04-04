package me.shail.MyBoutique.dto;

public record PaymentDto(
        Long id,
        String paypalPaymentId,
        String status,
        Long orderId) {
}
