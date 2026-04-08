package me.shail.myboutique_commons.dto;

public record OrderItemDto(
        Long id,
        Long quantity,
        Long productId,
        Long orderId) {
}
