package com.example.cosinusexam.LibrarySystem.controller;

import com.example.cosinusexam.LibrarySystem.dto.Request.LoginDTO;
import com.example.cosinusexam.LibrarySystem.dto.Request.ResetPasswordDTO;
import com.example.cosinusexam.LibrarySystem.dto.Request.UserCr;
import com.example.cosinusexam.LibrarySystem.dto.Request.VerifyDTO;
import com.example.cosinusexam.LibrarySystem.dto.Response.TokenDTO;
import com.example.cosinusexam.LibrarySystem.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PermitAll
    @PostMapping("/sign-up")
    public ResponseEntity<String> create (@RequestBody UserCr user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.create(user));
    }

    @PermitAll
    @PostMapping("/verify")
    public ResponseEntity<TokenDTO> verify(
            @Valid @RequestBody VerifyDTO verifyDTO
    ) {
        return ResponseEntity.ok(authService.verify(verifyDTO.getEmail(), verifyDTO.getVerificationCode()));
    }

    @PermitAll
    @GetMapping("/new-verification-code")
    public ResponseEntity<String> newVerificationCode(
            @RequestParam String email
    ) {
        return ResponseEntity.ok(authService.newVerifyCode(email));
    }

    @PermitAll
    @PostMapping("/sing-in")
    public ResponseEntity<TokenDTO> signIn(
            @Valid @RequestBody LoginDTO loginDTO
    ) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PermitAll
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestParam @Email String email
    ) {
        return ResponseEntity.ok(authService.forgotPassword(email));
    }

    @PermitAll
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @Valid @RequestBody ResetPasswordDTO resetPasswordDTO
    ) {
        return ResponseEntity.ok(authService.resetPassword(resetPasswordDTO));
    }



}
