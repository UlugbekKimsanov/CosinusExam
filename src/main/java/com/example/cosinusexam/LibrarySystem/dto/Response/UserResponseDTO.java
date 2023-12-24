package com.example.cosinusexam.LibrarySystem.dto.Response;


import com.example.cosinusexam.LibrarySystem.entity.enums.UserRole;
import com.example.cosinusexam.LibrarySystem.entity.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponseDTO {
    private String name;
    private String email;
    private UserStatus status;
    private UserRole role;
}