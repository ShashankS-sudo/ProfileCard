package com.example.ProfileCard.Service;

import com.example.ProfileCard.DTO.MyProfileResponse;
import com.example.ProfileCard.Exception.UserNotFoundException;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Repository.ProfileRepo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepo repo;

    @Autowired
    public ProfileService(ProfileRepo repo) {
        this.repo = repo;
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findByEmail(@NonNull @Email @NotBlank String email) {
        return repo.findByEmail(email).orElseThrow();
    };

    public boolean emailExists(String email) {
        return repo.existsByEmail(email);
    }

    public MyProfileResponse getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = repo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new MyProfileResponse(user);
    }
}
