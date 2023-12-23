package com.example.cosinusexam.LibrarySystem.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Polka extends BaseEntity{
    private Integer polkNumber;
    @ManyToOne(targetEntity = Javon.class,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Javon javon;
}
