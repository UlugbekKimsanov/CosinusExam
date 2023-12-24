package com.example.cosinusexam.LibrarySystem.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qavat extends BaseEntity{
    private Integer qavatNum;
    @ManyToOne(targetEntity = Kutubxona.class,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Kutubxona kutubxona;
}
