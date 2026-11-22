package com.example.ProfileCard.Controller;

import com.example.ProfileCard.DTO.DeleteResponse;
import com.example.ProfileCard.DTO.MyProfileResponse;
import com.example.ProfileCard.DTO.UpdatePasswordRequest;
import com.example.ProfileCard.DTO.UpdateResponseRequest;
import com.example.ProfileCard.Security.JWT.JwtService;
import com.example.ProfileCard.Service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService service;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<MyProfileResponse> getDetails(String token) {
        return ResponseEntity.ok(service.getMyProfile());
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody UpdateResponseRequest user) {
        return ResponseEntity.ok(service.updateProfile(user));
    }

    @PutMapping("/update/password")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest passwordRequest) {
        return ResponseEntity.ok(service.updatePassword(passwordRequest));
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody DeleteResponse deleteResponse) {
        return ResponseEntity.ok(service.deleteUser(deleteResponse));
    }
}
