package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Token Service implementation.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Method for token creation.
   */
  public String createToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(createExpiration())
        .sign(algorithm);
  }

  private Instant createExpiration() {
    return Instant.now().plus(40, ChronoUnit.DAYS);
  }
}
