package com.example.cosinusexam.LibrarySystem.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Polka extends BaseEntity{
    private Integer polkaNum;
    @ManyToOne(targetEntity = Javon.class,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Javon javon;
    @Max(value = 20, message = "20 tadan ko'p kitob sig'maydi")
    private Integer kitobCount;

}
