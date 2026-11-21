package com.example.ProfileCard.Controller;

import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class AdminController {
    private final ProfileService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.findAll());
    }
}
