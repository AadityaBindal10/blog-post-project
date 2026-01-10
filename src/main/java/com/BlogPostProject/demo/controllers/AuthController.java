package com.BlogPostProject.demo.controllers;

import com.BlogPostProject.demo.domain.dtos.AuthResponse;
import com.BlogPostProject.demo.domain.dtos.LoginRequest;
import com.BlogPostProject.demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthenticationService authenticationService;

    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400) ///  24 hours
                .build();
        return ResponseEntity.ok(authResponse);
    }
}
