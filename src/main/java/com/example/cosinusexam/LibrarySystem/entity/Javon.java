package com.example.cosinusexam.LibrarySystem.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Javon extends BaseEntity{
    @ManyToOne(targetEntity = Qavat.class,cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private Qavat qavat;
}
