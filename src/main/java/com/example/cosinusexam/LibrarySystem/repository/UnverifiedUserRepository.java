package com.example.cosinusexam.LibrarySystem.repository;

import com.example.cosinusexam.LibrarySystem.entity.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser, UUID> {

    UnverifiedUser findByEmail(String email);
}
