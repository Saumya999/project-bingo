package com.bingo.authentication.repository;

import com.bingo.authentication.model.Credentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICredentialsRepository extends MongoRepository<Credentials, String> {
}
