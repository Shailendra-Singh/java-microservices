package me.shail.MyBoutique.dto;

public record ReviewDto(
        Long id,
        String title,
        String description,
        Long rating) {
}
