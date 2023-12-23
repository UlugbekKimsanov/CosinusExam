package com.example.cosinusexam.LibrarySystem.repository;

import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface KitobRepository extends JpaRepository<Kitob, UUID> {
}
