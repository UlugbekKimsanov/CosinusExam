package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.Request.KitobCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.KitobResponseDto;
import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import com.example.cosinusexam.LibrarySystem.entity.Polka;
import com.example.cosinusexam.LibrarySystem.entity.enums.KitobStatus;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.KitobRepository;
import com.example.cosinusexam.LibrarySystem.repository.PolkaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitobService {
    private final ModelMapper modelMapper;
    private final KitobRepository kitobRepository;
    private final PolkaRepository polkaRepository;
    @SneakyThrows
    public KitobResponseDto create(KitobCr kitobCr) {
        Polka polka = polkaRepository.findById(kitobCr.getPolkaId())
                .orElseThrow(() -> new DataNotFoundException("Polka topilmadi!"));
        Kitob kitob = modelMapper.map(kitobCr, Kitob.class);
        kitob.setStatus(KitobStatus.ON_SHELF);
        kitob.setPolka(polka);
        System.out.println("kitob = " + kitob);
        System.out.println("polka.getKitobCount() = " + polka.getKitobCount());
        polka.setKitobCount(polka.getKitobCount()+1);
        System.out.println("polka.getKitobCount() = " + polka.getKitobCount());
        System.out.println("polka = " + polka);
        if(polka.getKitobCount() > 20){
            throw new BadRequestException("Polkaga 20tagacha kitob sigadi!");
        }
        polkaRepository.save(polka);
        kitobRepository.save(kitob);
        return modelMapper.map(kitob,KitobResponseDto.class);
    }

    public List<Kitob> getAll() {
        return kitobRepository.findAll();
    }
}
