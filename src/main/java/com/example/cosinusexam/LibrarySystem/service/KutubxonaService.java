package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.KutubxonaDto;
import com.example.cosinusexam.LibrarySystem.entity.Javon;
import com.example.cosinusexam.LibrarySystem.entity.Kutubxona;
import com.example.cosinusexam.LibrarySystem.entity.Polka;
import com.example.cosinusexam.LibrarySystem.entity.Qavat;
import com.example.cosinusexam.LibrarySystem.repository.JavonRepository;
import com.example.cosinusexam.LibrarySystem.repository.KutubxonaRepository;
import com.example.cosinusexam.LibrarySystem.repository.PolkaRepository;
import com.example.cosinusexam.LibrarySystem.repository.QavatRepositoy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KutubxonaService {
    private final KutubxonaRepository kutubxonaRepository;
    private final ModelMapper modelMapper;
    private final QavatRepositoy qavatRepositoy;
    private final JavonRepository javonRepository;
    private final PolkaRepository polkaRepository;
    public KutubxonaDto create(KutubxonaDto kutubxonaDto) {
        Kutubxona kutubxona = kutubxonaRepository.save(modelMapper.map(kutubxonaDto, Kutubxona.class));
        for (int i = 1; i <= 4; i++) {
            Qavat qavat = qavatRepositoy.save(new Qavat(i, kutubxona));
            for (int j = 1; j <= 20; j++) {
                Javon javon = javonRepository.save(new Javon(j,qavat));
                for (int p = 1; p <=10 ; p++) {
                    polkaRepository.save(new Polka(p,javon,0));
                }
            }
        }
        return kutubxonaDto;
    }


}
