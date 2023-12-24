package com.example.cosinusexam.LibrarySystem.dto.Response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO {

    private String accessToken;
    private Date accessTokenExpire;

    private String refreshToken;
    private Date refreshTokenExpire;
}