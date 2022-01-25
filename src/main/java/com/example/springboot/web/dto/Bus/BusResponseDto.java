package com.example.springboot.web.dto.Bus;

import com.example.springboot.domain.bus.Bus;
import com.example.springboot.domain.bus.BusStation;
import lombok.Getter;

@Getter
public class BusResponseDto {
    private Long id;
    private String name;
    private String number;

    public BusResponseDto(Bus entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.number = entity.getNumber();
    }
}
