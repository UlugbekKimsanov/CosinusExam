package com.example.cosinusexam.LibrarySystem.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum UserRole {
    ADMIN,
    MODERATOR,
    USER;
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
