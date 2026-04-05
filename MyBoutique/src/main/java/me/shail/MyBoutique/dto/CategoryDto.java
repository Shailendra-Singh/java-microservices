package me.shail.MyBoutique.dto;

import java.util.Set;

public record CategoryDto(
        Long id,
        String name,
        String description,
        Set<ProductDto> products) {
}
