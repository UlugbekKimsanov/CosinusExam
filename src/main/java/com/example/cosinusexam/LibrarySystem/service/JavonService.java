package com.example.cosinusexam.LibrarySystem.service;
import com.example.cosinusexam.LibrarySystem.dto.JavonDto;
import com.example.cosinusexam.LibrarySystem.dto.KutubxonaDto;
import com.example.cosinusexam.LibrarySystem.entity.Javon;
import com.example.cosinusexam.LibrarySystem.entity.Kutubxona;
import com.example.cosinusexam.LibrarySystem.repository.JavonRepository;
import com.example.cosinusexam.LibrarySystem.repository.KutubxonaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JavonService {
    private final JavonRepository javonRepository;
    private final ModelMapper modelMapper;
    public JavonDto create(JavonDto javonDto) {
        javonRepository.save(modelMapper.map(javonDto, Javon.class));
        return javonDto;
    }
}
