package com.example.springboot.web.dto.Bus;

import com.example.springboot.domain.bus.Bus;
import com.example.springboot.domain.bus.BusStation;
import com.example.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BusListResponseDto {
    private Long id;
    private String region;
    private String name;
    private String number;

    public BusListResponseDto(Bus entity){
        this.id = entity.getId();
        this.region = entity.getRegion();
        this.name = entity.getName();
        this.number = entity.getNumber();
    }
}
