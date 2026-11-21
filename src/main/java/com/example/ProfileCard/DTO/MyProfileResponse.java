package com.example.ProfileCard.DTO;

import com.example.ProfileCard.Model.Gender;
import com.example.ProfileCard.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
@AllArgsConstructor
public class MyProfileResponse {
    private String name;
    private int age;
    private String email;
    private Gender gender;
    private String state;
    private String country;
    private String panNumber;
    private String driversLicense;

    public MyProfileResponse(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.state = user.getState();
        this.country = user.getCountry();
        this.panNumber = user.getPanNumber();
        this.driversLicense = user.getDriversLicense();
    }
}
