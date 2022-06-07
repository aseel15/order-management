package com.example.wholeProject.controller;

import com.example.wholeProject.dto.AuthToken;
import com.example.wholeProject.dto.LoginDto;
import com.example.wholeProject.dto.UserDto;
import com.example.wholeProject.entities.User;
import com.example.wholeProject.security.JwtTokenProvider;
import com.example.wholeProject.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/token")
public class AuthenticationResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    //@RequestMapping(value = "generate-token", method = RequestMethod.POST)
    @PostMapping("/generate-token")
    public ResponseEntity<AuthToken> login(@RequestBody LoginDto loginDto) throws AuthenticationException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        final UserDto userDto = userService.getByUsername(loginDto.getUsername());
        final String token = jwtTokenProvider.generateToken(userDto);
        return ResponseEntity.ok().body(new AuthToken(token, userDto.getUsername()));

    }
}
