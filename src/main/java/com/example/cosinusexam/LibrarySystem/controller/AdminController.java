package com.example.cosinusexam.LibrarySystem.controller;

import com.example.cosinusexam.LibrarySystem.dto.*;
import com.example.cosinusexam.LibrarySystem.dto.Request.KitobCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.KitobResponseDto;
import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import com.example.cosinusexam.LibrarySystem.entity.Qavat;
import com.example.cosinusexam.LibrarySystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final KutubxonaService kutubxonaService;
    private final QavatService qavatService;
    private final JavonService javonService;
    private final PolkaService polkaService;
    private final KitobService kitobService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-library")
    public ResponseEntity<KutubxonaDto> create(@RequestBody KutubxonaDto kutubxonaDto) {
        return ResponseEntity.status(200).body(kutubxonaService.create(kutubxonaDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-qavat")
    public ResponseEntity<Qavat> create(@RequestBody QavatDto qavatDto) {
        return ResponseEntity.status(200).body(qavatService.create(qavatDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-javon")
    public ResponseEntity<JavonDto> create(@RequestBody JavonDto javonDto) {
        return ResponseEntity.status(200).body(javonService.create(javonDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get_all_books")
    public ResponseEntity<List<Kitob>> getBooks(){
        return ResponseEntity.ok(kitobService.getAll());
    }


}
