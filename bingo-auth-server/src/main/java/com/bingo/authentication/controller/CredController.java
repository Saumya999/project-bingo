package com.bingo.authentication.controller;

import com.bingo.authentication.model.Credentials;
import com.bingo.authentication.service.ICredentialManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/bingo/auth/v1")
public class CredController {

    private final ICredentialManager credentialManager;

    @PostMapping("/register")
    public Credentials createCred(@RequestBody Credentials cred) {
        return credentialManager.createCredentialsData(cred);
    }

    @PostMapping("/login")
    public String createToken(@RequestBody Credentials cred) {
        return credentialManager.generateToken(cred);
    }
}
