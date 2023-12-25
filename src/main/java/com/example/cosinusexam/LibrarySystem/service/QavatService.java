package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.QavatDto;
import com.example.cosinusexam.LibrarySystem.entity.Javon;
import com.example.cosinusexam.LibrarySystem.entity.Polka;
import com.example.cosinusexam.LibrarySystem.entity.Qavat;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.JavonRepository;
import com.example.cosinusexam.LibrarySystem.repository.KutubxonaRepository;
import com.example.cosinusexam.LibrarySystem.repository.PolkaRepository;
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
    private final JavonRepository javonRepository;
    private final PolkaRepository polkaRepository;
    public Qavat create(QavatDto qavatDto) {
        Qavat qavat = modelMapper.map(qavatDto, Qavat.class);
        qavat.setKutubxona(kutubxonaRepository.findById(qavatDto.getKutubxona_id())
                .orElseThrow(() -> new DataNotFoundException("Kutubxona topilmadi")));
        for (int j = 1; j <= 20; j++) {
            Javon javon = javonRepository.save(new Javon(j,qavat));
            for (int p = 1; p <=10 ; p++) {
                polkaRepository.save(new Polka(p,javon,0));
            }
        }
        return qavatRepository.save(qavat);
    }
}
