package com.example.ProfileCard.Controller;

import com.example.ProfileCard.DTO.MyProfileResponse;
import com.example.ProfileCard.Security.JWT.JwtService;
import com.example.ProfileCard.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final ProfileService profileService;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<MyProfileResponse> getDetails(String token) {
        return ResponseEntity.ok(profileService.getMyProfile());
    }
}
