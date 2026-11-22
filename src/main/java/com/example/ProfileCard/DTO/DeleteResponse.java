package com.example.ProfileCard.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteResponse {

    @NotBlank(message = "Password is required")
    private String password;
}