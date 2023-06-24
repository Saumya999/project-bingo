package com.bingo.authentication.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtility {

   public String getToken(String userName) {
      Map<String, Object> claims  = new HashMap<>();
      return createToken(claims, userName);
   }

   private String createToken(Map<String, Object> claims, String userName) {
      return Jwts.builder()
              .setClaims(claims)
              .setIssuer(userName)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
              .signWith(getSignWithKey(), SignatureAlgorithm.HS256)
              .compact();
   }

   private Key getSignWithKey() {
      final String jwtSecret = "b3JGVmpWUStOWmxuWE4zY04yWk4rUVM3eDRzVzdqeWxYYXlZYmg1RElpUT0=";
      byte [] keyBytes = Decoders.BASE64.decode(jwtSecret);
      return Keys.hmacShaKeyFor(keyBytes);
   }

}
