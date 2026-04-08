package me.shail.myboutique_commons.dto;

public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String telephone) {
}
