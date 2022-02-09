package com.example.springboot.domain.bus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BusFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String region;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String email;

    @Builder
    public BusFavorite(String region, String name, String number, String email){
        this.region = region;
        this.name = name;
        this.number = number;
        this.email = email;
    }
}
