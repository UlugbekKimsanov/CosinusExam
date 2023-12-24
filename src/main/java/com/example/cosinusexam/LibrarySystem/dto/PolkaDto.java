package com.example.cosinusexam.LibrarySystem.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolkaDto {
    private Integer polkaNum;
    private UUID javonId;
}
