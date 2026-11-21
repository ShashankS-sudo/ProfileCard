package com.example.ProfileCard.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginUser {
    private String email;
    private String password;
}
