package com.example.cosinusexam.LibrarySystem.dto.Request;

import com.example.cosinusexam.LibrarySystem.entity.enums.KitobStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitobCr {
    private String name;
    private String author;
    private Integer page;
    private UUID polkaId;
}
