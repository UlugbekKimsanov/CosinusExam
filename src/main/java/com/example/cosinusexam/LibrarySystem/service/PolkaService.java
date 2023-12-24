package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.PolkaDto;
import com.example.cosinusexam.LibrarySystem.entity.Polka;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.JavonRepository;
import com.example.cosinusexam.LibrarySystem.repository.PolkaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolkaService {
    private final PolkaRepository polkaRepository;
    private final ModelMapper modelMapper;
    private final JavonRepository javonRepository;
    public PolkaDto create(PolkaDto polkaDto) {
        Polka polka = modelMapper.map(polkaDto, Polka.class);
        polka.setJavon(javonRepository.findById(polkaDto.getJavonId())
                .orElseThrow(() -> new DataNotFoundException("Javon topilmadi!")));
        polkaRepository.save(polka);
        return polkaDto;
    }
}
