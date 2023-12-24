package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.KitobDto;
import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import com.example.cosinusexam.LibrarySystem.repository.KitobRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitobService {
    private final ModelMapper modelMapper;
    private final KitobRepository kitobRepository;
    public KitobDto create(KitobDto kitobDto) {
        kitobRepository.save(modelMapper.map(kitobDto, Kitob.class));
        return kitobDto;
    }
}
