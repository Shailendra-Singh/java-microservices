package me.shail.myboutique_commons.dto;

public record CartDto(
        Long id,
        Long orderId,
        CustomerDto customerDto,
        String status) {
}
