package com.example.springboot.domain.bus;

import com.example.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Bus extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String region;

    @Column(length=500, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String number;

    @Builder
    public Bus(String region, String name, String number){
        this.region = region;
        this.name = name;
        this.number = number;
    }
}
