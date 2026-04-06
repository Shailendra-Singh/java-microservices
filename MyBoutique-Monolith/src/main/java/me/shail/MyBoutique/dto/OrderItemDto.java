package me.shail.MyBoutique.dto;

public record OrderItemDto(
        Long id,
        Long quantity,
        Long productId,
        Long orderId) {
}
