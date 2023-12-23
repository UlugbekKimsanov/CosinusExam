package com.example.cosinusexam.LibrarySystem.dto.Request;
import com.example.cosinusexam.LibrarySystem.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCr {
    private String name;
    private String surname;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
