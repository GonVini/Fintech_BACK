package br.com.fiap.fintech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank @Size(max = 150) String fullName,
        @NotBlank @Email @Size(max = 120) String email,
        @NotBlank @Size(min = 6, max = 255) String password
) {
}
