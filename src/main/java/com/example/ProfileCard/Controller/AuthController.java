package com.example.ProfileCard.Controller;

import com.example.ProfileCard.DTO.LoginResponse;
import com.example.ProfileCard.DTO.LoginUser;
import com.example.ProfileCard.DTO.RegisterUser;
import com.example.ProfileCard.Exception.EmailAlreadyExistsException;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Service.AuthenticationService;
import com.example.ProfileCard.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ProfileService service;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody RegisterUser registerUser) {

        if (service.emailExists(registerUser.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "An account with this email already exists"
            );
        }

        authenticationService.register(registerUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginUser loginUser) {
        return ResponseEntity.ok(authenticationService.login(loginUser));
    }
}
