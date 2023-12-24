package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.QavatDto;
import com.example.cosinusexam.LibrarySystem.entity.Qavat;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.KutubxonaRepository;
import com.example.cosinusexam.LibrarySystem.repository.QavatRepositoy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QavatService {
    private final QavatRepositoy qavatRepository;
    private final ModelMapper modelMapper;
    private final KutubxonaRepository kutubxonaRepository;
    public Qavat create(QavatDto qavatDto) {
        Qavat qavat = modelMapper.map(qavatDto, Qavat.class);
        qavat.setKutubxona(kutubxonaRepository.findById(qavatDto.getKutubxona_id())
                .orElseThrow(() -> new DataNotFoundException("Kutubxona topilmadi")));
        return qavatRepository.save(qavat);
    }
}
