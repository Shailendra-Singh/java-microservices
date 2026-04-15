package me.shail.MyBoutique.common.dto;

public record CustomerDto(
                Long id,
                String firstName,
                String lastName,
                String email,
                String telephone) {
}
