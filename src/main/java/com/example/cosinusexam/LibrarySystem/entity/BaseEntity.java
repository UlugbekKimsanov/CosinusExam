package com.example.cosinusexam.LibrarySystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;

}
