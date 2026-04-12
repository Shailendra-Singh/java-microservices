package me.shail.myboutique_commons.dto;

public record ReviewDto(
        Long id,
        String title,
        String description,
        Long rating) {
}
