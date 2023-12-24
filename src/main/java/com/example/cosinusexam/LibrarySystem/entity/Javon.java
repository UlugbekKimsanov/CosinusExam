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
    private Integer javonNum;
    @ManyToOne(targetEntity = Qavat.class,cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private Qavat qavat;
}
