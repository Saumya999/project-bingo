package com.bingo.authentication.config;

import com.bingo.authentication.model.Credentials;
import com.bingo.authentication.model.CustomUserDetails;
import com.bingo.authentication.repository.ICredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ICredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials =  Optional.of(credentialsRepository.findById(username)).get().orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(credentials);
    }
}
