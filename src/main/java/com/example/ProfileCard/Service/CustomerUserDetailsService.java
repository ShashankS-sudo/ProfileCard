package com.example.ProfileCard.Service;

import com.example.ProfileCard.Repository.ProfileRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final ProfileRepo repo;

    public CustomerUserDetailsService(ProfileRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return repo.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username
                        )
                );
    }
}
