package com.example.cosinusexam.LibrarySystem.repository;

import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<Object> findByEmail(String email);

    boolean existsByEmail(String email);
}
