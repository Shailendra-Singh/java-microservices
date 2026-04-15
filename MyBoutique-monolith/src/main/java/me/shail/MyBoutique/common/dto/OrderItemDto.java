package me.shail.MyBoutique.common.dto;

public record OrderItemDto(
                Long id,
                Long quantity,
                Long productId,
                Long orderId) {
}
