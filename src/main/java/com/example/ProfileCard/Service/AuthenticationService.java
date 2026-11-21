
package com.example.ProfileCard.Service;

import com.example.ProfileCard.DTO.LoginResponse;
import com.example.ProfileCard.DTO.LoginUser;
import com.example.ProfileCard.DTO.RegisterUser;
import com.example.ProfileCard.Model.Role;
import com.example.ProfileCard.Model.User;
import com.example.ProfileCard.Repository.ProfileRepo;
import com.example.ProfileCard.Security.JWT.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ProfileRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterUser registerUser) {

        User user = User.builder()
                .name(registerUser.getName())
                .age(registerUser.getAge())
                .gender(registerUser.getGender())
                .country(registerUser.getCountry())
                .state(registerUser.getState())
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .panNumber(registerUser.getPanNumber())
                .driversLicense(registerUser.getDriversLicense())
                .role(Role.User)
                .build();

        repo.save(user);
    }

    public LoginResponse login(LoginUser loginUser) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        String token = jwtService.generateToken(loginUser.getEmail());
        return new LoginResponse(token);
    }
}
