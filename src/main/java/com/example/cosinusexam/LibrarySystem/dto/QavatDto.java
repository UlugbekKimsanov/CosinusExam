package com.example.cosinusexam.LibrarySystem.dto;

import com.example.cosinusexam.LibrarySystem.entity.Kutubxona;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QavatDto {
    private Integer qavatNum;
    private UUID kutubxona_id;
}
