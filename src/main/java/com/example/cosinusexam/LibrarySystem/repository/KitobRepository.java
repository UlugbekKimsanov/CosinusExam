package com.example.cosinusexam.LibrarySystem.repository;

import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import com.example.cosinusexam.LibrarySystem.entity.enums.KitobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface KitobRepository extends JpaRepository<Kitob, UUID> {
    List<Kitob> findByStatus(KitobStatus status);
    List<Kitob> findByNameContaining(String name);
    List<Kitob> findByAuthorContaining(String authorName);
}
