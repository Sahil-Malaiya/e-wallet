package com.swiftpay.e_wallet.controller;

import com.swiftpay.e_wallet.dto.AuthRequest;
import com.swiftpay.e_wallet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        String response = authService.authenticate(authRequest);
        return ResponseEntity.ok(response);
    }
}
