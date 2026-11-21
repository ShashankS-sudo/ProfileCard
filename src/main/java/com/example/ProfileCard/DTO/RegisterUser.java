package com.example.ProfileCard.DTO;

import com.example.ProfileCard.Model.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterUser {
    @NonNull
    @NotBlank
    private String name;
    private int age;
    @NonNull
    @Email
    @NotBlank
    private String email;
    @NonNull
    @Size(min = 8)
    private String password;
    private Gender gender;
    @NotBlank
    private String country;
    @NotBlank
    private String state;
    @NotBlank
    private String panNumber;
    @NotBlank
    private String driversLicense;
}
