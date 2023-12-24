package com.example.cosinusexam.LibrarySystem.dto.Request;

import com.example.cosinusexam.LibrarySystem.entity.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoleDTO {
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
