package com.example.cosinusexam.LibrarySystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitobDto {
    private String name;
    private String author;
    private Integer page;
}
