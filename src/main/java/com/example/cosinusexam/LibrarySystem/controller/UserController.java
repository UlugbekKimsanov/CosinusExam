package com.example.cosinusexam.LibrarySystem.controller;
import com.example.cosinusexam.LibrarySystem.dto.Request.UserCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.UserResponseDTO;
import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import com.example.cosinusexam.LibrarySystem.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;

    @PermitAll
    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserCr user, Principal principal){
        return ResponseEntity.ok(userService.update(user, principal));
    }

    @PermitAll
    @GetMapping("/my_profile")
    public ResponseEntity<UserResponseDTO> myProfile(Principal principal){
        return ResponseEntity.ok(userService.myProfile(principal));
    }

    @PermitAll
    @DeleteMapping("/delete")
    public  ResponseEntity<String > delete(Principal principal){
        return ResponseEntity.status(200).body(userService.delete(principal));
    }

}
