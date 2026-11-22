package com.example.ProfileCard.Controller;

import com.example.ProfileCard.DTO.AdminUserResponse;
import com.example.ProfileCard.Exception.UserNotFoundException;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProfileService service;

    @GetMapping("/{id}")
    public ResponseEntity<AdminUserResponse> getUser(@PathVariable Long id) {
        User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return ResponseEntity.ok(new AdminUserResponse(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getUsers() {
        List<AdminUserResponse> users = service.findAll()
                .stream()
                .map(AdminUserResponse::new)
                .toList();

        return ResponseEntity.ok(users);
    }
}
