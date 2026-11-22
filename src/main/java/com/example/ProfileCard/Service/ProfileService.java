package com.example.ProfileCard.Service;

import com.example.ProfileCard.DTO.DeleteResponse;
import com.example.ProfileCard.DTO.MyProfileResponse;
import com.example.ProfileCard.DTO.UpdatePasswordRequest;
import com.example.ProfileCard.DTO.UpdateResponseRequest;
import com.example.ProfileCard.Exception.PasswordError;
import com.example.ProfileCard.Exception.UserNotFoundException;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Repository.ProfileRepo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepo repo;
    private final PasswordEncoder passwordEncoder;

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

    public Page<User> getUsers(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Transactional
    public String updateProfile(UpdateResponseRequest updateUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = repo.findByEmail(username).orElseThrow();
        user.update(updateUser);
        return "Update Successfull";
    }

    @Transactional
    public String updatePassword(UpdatePasswordRequest passwordRequest) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = repo.findByEmail(username).orElseThrow();

        // 1. Verify current password
        if (!passwordEncoder.matches(
                passwordRequest.getCurrentPassword(),
                user.getPassword())) {

            throw new PasswordError("Current password is incorrect");
        }

        // 2. Verify new password confirmation
        if (!passwordRequest.getNewPassword()
                .equals(passwordRequest.getConfirmPassword())) {

            throw new PasswordError("New passwords do not match");
        }

        // 3. Encode the new password
        String encodedPassword = passwordEncoder.encode(passwordRequest.getNewPassword());
        user.setPassword(encodedPassword);
        user.setTokenVersion(user.getTokenVersion() + 1);

        return "Password changed successfully";
    }

    @Transactional
    public String deleteUser(DeleteResponse deleteResponse) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = repo.findByEmail(username).orElseThrow();

        if(!passwordEncoder.matches(deleteResponse.getPassword(), user.getPassword())) {
            throw new PasswordError("Incorrect Password");
        }

        repo.deleteByEmail(username);
        return "User Deleted Successfully";
    }
}
