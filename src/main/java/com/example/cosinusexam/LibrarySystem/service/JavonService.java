package com.example.cosinusexam.LibrarySystem.service;
import com.example.cosinusexam.LibrarySystem.dto.JavonDto;
import com.example.cosinusexam.LibrarySystem.dto.KutubxonaDto;
import com.example.cosinusexam.LibrarySystem.entity.Javon;
import com.example.cosinusexam.LibrarySystem.entity.Kutubxona;
import com.example.cosinusexam.LibrarySystem.entity.Qavat;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.JavonRepository;
import com.example.cosinusexam.LibrarySystem.repository.KutubxonaRepository;
import com.example.cosinusexam.LibrarySystem.repository.QavatRepositoy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JavonService {
    private final JavonRepository javonRepository;
    private final ModelMapper modelMapper;
    private final QavatRepositoy qavatRepositoy;
    public JavonDto create(JavonDto javonDto) {
        Qavat qavat = qavatRepositoy.findById(javonDto.getQavatId())
                .orElseThrow(() -> new DataNotFoundException("Qavat not found!"));
        Javon javon = modelMapper.map(javonDto, Javon.class);
        javon.setQavat(qavat);
        javonRepository.save(javon);
        return javonDto;
    }
}
