package com.example.cosinusexam.LibrarySystem.dto.Request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    private String password;
}
