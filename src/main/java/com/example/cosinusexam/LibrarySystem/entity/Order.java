package com.example.cosinusexam.LibrarySystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {
    @OneToOne
    private Kitob kitob;
    private LocalDateTime expiryDate;
    @OneToOne
    UserEntity owner;
    public Order(Kitob kitob,UserEntity owner){
        this.owner = owner;
        this.kitob = kitob;
        this.expiryDate = LocalDateTime.now().plusDays(3);
    }
}
