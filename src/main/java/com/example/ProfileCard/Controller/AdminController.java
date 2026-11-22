package com.example.ProfileCard.Controller;

import com.example.ProfileCard.DTO.AdminUserResponse;
import com.example.ProfileCard.Exception.UserNotFoundException;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<AdminUserResponse>> getUsers(Pageable page) {
        Page<AdminUserResponse> users = service.getUsers(page).map(AdminUserResponse::new);
        return ResponseEntity.ok(users);
    }
}
