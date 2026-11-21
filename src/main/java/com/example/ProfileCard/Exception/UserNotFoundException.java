package com.example.ProfileCard.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
