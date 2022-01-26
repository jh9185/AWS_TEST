package com.example.springboot.domain.bus;

import com.example.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BusStation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private Long seq;

    @Column(nullable = false)
    private Long posx;

    @Column(nullable = false)
    private Long posy;

    @Builder
    public BusStation(String name, String number, Long seq, Long posx, Long posy){
        this.name = name;
        this.number = number;
        this.region = region;
        this.seq = seq;
        this.posx = posx;
        this.posy = posy;
    }
}
