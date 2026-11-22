package com.example.ProfileCard.DTO;

import com.example.ProfileCard.Model.Gender;
import com.example.ProfileCard.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminUserResponse {
    private String name;
    private int age;
    private String email;
    private Gender gender;
    private String state;
    private String country;

    public AdminUserResponse(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.state = user.getState();
        this.country = user.getCountry();
    }
}
