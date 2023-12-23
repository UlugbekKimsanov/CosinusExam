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
public class Kutubxona extends BaseEntity{
    private String name;
//    @OneToMany(targetEntity = Qavat.class,cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<Qavat> qavatlar;
}
