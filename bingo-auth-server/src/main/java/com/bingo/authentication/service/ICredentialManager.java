package com.bingo.authentication.service;

import com.bingo.authentication.model.Credentials;

public interface ICredentialManager {
    Credentials createCredentialsData (Credentials cred);

    String generateToken(Credentials cred);
}
