package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.PolkaDto;
import com.example.cosinusexam.LibrarySystem.entity.Polka;
import com.example.cosinusexam.LibrarySystem.repository.PolkaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolkaService {
    private final PolkaRepository polkaRepository;
    private final ModelMapper modelMapper;
    public PolkaDto create(PolkaDto polkaDto) {
        polkaRepository.save(modelMapper.map(polkaDto, Polka.class));
        return polkaDto;
    }
}
