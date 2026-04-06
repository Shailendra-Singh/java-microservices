package me.shail.MyBoutique.dto;

public record CartDto(
        Long id,
        Long orderId,
        CustomerDto customerDto,
        String status) {
}
