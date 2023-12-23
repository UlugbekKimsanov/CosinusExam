package com.example.cosinusexam.LibrarySystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kitob extends BaseEntity {
    private String name;
    private String author;
    private Integer page;
    @ManyToOne(targetEntity = Polka.class, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Polka polka;
}