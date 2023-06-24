package in.crossdimension.apigwServer.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtility {

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignWithKey()).build().parseClaimsJws(token);
    }

    private Key getSignWithKey() {
        final String jwtSecret = "b3JGVmpWUStOWmxuWE4zY04yWk4rUVM3eDRzVzdqeWxYYXlZYmg1RElpUT0=";
        byte [] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
