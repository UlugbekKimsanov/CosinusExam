package com.example.cosinusexam.LibrarySystem.controller;

import com.example.cosinusexam.LibrarySystem.dto.Request.KitobCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.KitobResponseDto;
import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import com.example.cosinusexam.LibrarySystem.entity.Order;
import com.example.cosinusexam.LibrarySystem.service.*;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/moderator")
@RequiredArgsConstructor
public class ModeratorController {
    private final KitobService kitobService;
    private final OrderService orderService;
    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping("/create-kitob")
    public ResponseEntity<KitobResponseDto> create(@RequestBody KitobCr kitobCr) {
        return ResponseEntity.status(200).body(kitobService.create(kitobCr));
    }
    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/get_all_books")
    public ResponseEntity<List<Kitob>> getBooks(){
        return ResponseEntity.ok(kitobService.getAll());
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/see_orders")
    private ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }

}
