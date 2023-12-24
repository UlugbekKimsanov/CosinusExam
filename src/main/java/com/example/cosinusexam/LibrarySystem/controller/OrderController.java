package com.example.cosinusexam.LibrarySystem.controller;

import com.example.cosinusexam.LibrarySystem.dto.Response.KitobResponseDto;
import com.example.cosinusexam.LibrarySystem.entity.Order;
import com.example.cosinusexam.LibrarySystem.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
    @GetMapping("/see_books")
    public ResponseEntity<List<KitobResponseDto>> getAvailableBooks(){
        return ResponseEntity.ok(orderService.getAvailableBooks());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
    @PostMapping("/add_to_order")
    public ResponseEntity<String> addOrder(@RequestParam UUID bookId, Principal principal){
        return ResponseEntity.ok(orderService.addOrder(bookId,principal));
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
    @PostMapping("/get_rent")
    public ResponseEntity<String> getRent(@RequestParam UUID bookId, Principal principal){
        return ResponseEntity.ok(orderService.getRent(bookId,principal));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
    @GetMapping("/my_orders")
    public ResponseEntity<List<Order>> myOrders(Principal principal){
        return ResponseEntity.ok(orderService.getOrders(principal));
    }


}
