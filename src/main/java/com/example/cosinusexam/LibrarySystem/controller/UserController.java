package com.example.cosinusexam.LibrarySystem.controller;
import com.example.cosinusexam.LibrarySystem.dto.Request.AuthDto;
import com.example.cosinusexam.LibrarySystem.dto.Request.UserCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.JwtResponse;
import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import com.example.cosinusexam.LibrarySystem.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;
    @PermitAll
    @PostMapping("/sign-in")
    public JwtResponse signIn(@Valid @RequestBody AuthDto dto) {
        return userService.signIn(dto);
    }

    @PermitAll
    @PutMapping("/update")
    public ResponseEntity<UserEntity> update(@RequestBody UserCr user, Principal principal){
        return ResponseEntity.ok(userService.update(user, principal));
    }
    @PermitAll
    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody UserCr userCr){
        return ResponseEntity.ok(userService.create(userCr));
    }
    @PermitAll
    @GetMapping("/my_profile")
    public ResponseEntity<UserEntity> myProfile(@RequestBody Principal principal){
        return ResponseEntity.ok(userService.myProfile(principal));
    }

    @PermitAll
    @DeleteMapping("/delete")
    public  ResponseEntity<String > delete(Principal principal){
        return ResponseEntity.status(200).body(userService.delete(principal));
    }

}
