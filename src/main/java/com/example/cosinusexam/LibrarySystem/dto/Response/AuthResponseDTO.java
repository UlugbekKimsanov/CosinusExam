package com.example.cosinusexam.LibrarySystem.dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO<T> {

    private String message;
    private T object;

}