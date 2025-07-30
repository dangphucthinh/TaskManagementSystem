package taskmanagementsystem.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    // JWT secret key, injected from application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    // JWT expiration time in milliseconds, injected from application.properties
    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    /**
     * Generates a JWT token for the given username.
     * @param username the username for which the token is generated
     * @return a signed JWT token as String
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
