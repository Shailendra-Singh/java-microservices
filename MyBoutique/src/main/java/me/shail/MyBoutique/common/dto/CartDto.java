package me.shail.MyBoutique.common.dto;

public record CartDto(
                Long id,
                Long orderId,
                CustomerDto customerDto,
                String status) {
}
