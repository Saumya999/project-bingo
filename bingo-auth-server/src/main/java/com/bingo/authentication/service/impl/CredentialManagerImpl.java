package com.bingo.authentication.service.impl;

import com.bingo.authentication.model.Credentials;
import com.bingo.authentication.repository.ICredentialsRepository;
import com.bingo.authentication.service.ICredentialManager;
import com.bingo.authentication.util.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CredentialManagerImpl implements ICredentialManager {

    private final ICredentialsRepository credentialsRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    @Override
    public Credentials createCredentialsData(Credentials cred) {
        cred.setPassword(passwordEncoder.encode(cred.getPassword()));
        return credentialsRepository.save(cred);
    }

    @Override
    public String generateToken(Credentials cred) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cred.getUserName(), cred.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new AccessDeniedException("Invalid access");
        }
        return jwtUtility.getToken(cred.getUserName());
    }
}
