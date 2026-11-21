package com.example.ProfileCard.Repository;

import com.example.ProfileCard.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String username);
}
