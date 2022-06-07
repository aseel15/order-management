package com.example.wholeProject.security;

import com.example.wholeProject.exception.BlogAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public final static String SIGNING_KEY = "sela555";

    UserDetailsService userDetailsService;

    JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = "Authorization";
        String prefix = "Bearer ";
        String userName = null;
        String authToken = null;

        if(header != null && header.startsWith(prefix)){
            authToken = header.replace(prefix, "");
            try {
                userName = jwtTokenProvider.getUserNameFromToken(authToken);
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
        }else {
            logger.warn("could not find bearer header");
        }
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if(jwtTokenProvider.validateToken(authToken, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }


        filterChain.doFilter(request, response);
    }
}
