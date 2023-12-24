package com.example.cosinusexam.LibrarySystem.entity;

import com.example.cosinusexam.LibrarySystem.entity.enums.KitobStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Kitob extends BaseEntity {
    private String name;
    private String author;
    private Integer page;
    @ManyToOne(targetEntity = Polka.class, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Polka polka;
    @Enumerated(EnumType.STRING)
    private KitobStatus status;
}