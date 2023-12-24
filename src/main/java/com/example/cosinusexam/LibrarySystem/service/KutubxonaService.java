package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.KutubxonaDto;
import com.example.cosinusexam.LibrarySystem.entity.Kutubxona;
import com.example.cosinusexam.LibrarySystem.repository.KutubxonaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KutubxonaService {
    private final KutubxonaRepository kutubxonaRepository;
    private final ModelMapper modelMapper;
    public KutubxonaDto create(KutubxonaDto kutubxonaDto) {
        kutubxonaRepository.save(modelMapper.map(kutubxonaDto, Kutubxona.class));
        return kutubxonaDto;
    }


}
