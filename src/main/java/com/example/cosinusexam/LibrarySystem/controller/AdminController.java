package com.example.cosinusexam.LibrarySystem.controller;

import com.example.cosinusexam.LibrarySystem.dto.*;
import com.example.cosinusexam.LibrarySystem.entity.Qavat;
import com.example.cosinusexam.LibrarySystem.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    @PostMapping("/create-polka")
    public ResponseEntity<PolkaDto> create(@RequestBody PolkaDto polkaDto) {
        return ResponseEntity.status(200).body(polkaService.create(polkaDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-kitob")
    public ResponseEntity<KitobDto> create(@RequestBody KitobDto kitobDto) {
        return ResponseEntity.status(200).body(kitobService.create(kitobDto));
    }
}
