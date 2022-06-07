package com.example.wholeProject.security;

import com.example.wholeProject.dto.UserDto;
import com.example.wholeProject.entities.User;
import com.example.wholeProject.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;


    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public boolean validateToken(String authToken, UserDetails userDetails){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

    public String generateToken(UserDto userDto){
        String username = userDto.getUsername();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    public String getUserNameFromToken(String authToken){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
                .getBody();
        return claims.getSubject();
    }

}
