package me.shail.MyBoutique.dto;

public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String telephone) {
}
