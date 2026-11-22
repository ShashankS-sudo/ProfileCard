package com.example.ProfileCard.Model;

import com.example.ProfileCard.DTO.UpdateResponseRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String country;
    private String state;
    private String panNumber;
    private String driversLicense;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Integer tokenVersion = 0;

    @Lob
    private byte[] image;

    public void update(UpdateResponseRequest updateResponseRequest) {
        this.name = updateResponseRequest.getName();
        this.age = updateResponseRequest.getAge();
        this.gender = updateResponseRequest.getGender();
        this.state = updateResponseRequest.getState();
        this.country = updateResponseRequest.getCountry();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @JsonIgnore
    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
