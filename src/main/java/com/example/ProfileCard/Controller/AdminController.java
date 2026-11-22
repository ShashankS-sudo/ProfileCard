package com.example.ProfileCard.Controller;

import com.example.ProfileCard.DTO.AdminUserResponse;
import com.example.ProfileCard.Exception.UserNotFoundException;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<AdminUserResponse>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException("Page size must be between 1 and 100");
        }

        List<String> allowedFields = List.of("name", "email", "age");

        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sorting field");
        }

        Sort.Direction sortDirection;

        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sorting direction. Use asc or desc");
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, sortBy)
        );

        Page<AdminUserResponse> users = service.getUsers(pageable)
                .map(AdminUserResponse::new);

        return ResponseEntity.ok(users);
    }
}
