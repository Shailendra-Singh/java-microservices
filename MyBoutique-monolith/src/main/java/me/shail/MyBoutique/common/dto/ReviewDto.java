package me.shail.MyBoutique.common.dto;

public record ReviewDto(
                Long id,
                String title,
                String description,
                Long rating) {
}
