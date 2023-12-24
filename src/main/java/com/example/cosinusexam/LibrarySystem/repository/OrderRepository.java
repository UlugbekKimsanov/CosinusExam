package com.example.cosinusexam.LibrarySystem.repository;

import com.example.cosinusexam.LibrarySystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByKitob_Id(UUID kitobId);
    List<Order> findAllByOwnerEmail(String email);
}
