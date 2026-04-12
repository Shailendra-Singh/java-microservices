package me.shail.MyBoutique.common.dto;

import java.util.Set;

public record CategoryDto(
                Long id,
                String name,
                String description,
                Set<ProductDto> products) {
}
