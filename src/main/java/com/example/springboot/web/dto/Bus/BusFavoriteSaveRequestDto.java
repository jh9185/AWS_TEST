package com.example.springboot.web.dto.Bus;

import com.example.springboot.domain.bus.BusFavorite;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusFavoriteSaveRequestDto {
    private String region;
    private String name;
    private String number;

    public BusFavoriteSaveRequestDto(BusFavorite entity){
        this.region = entity.getRegion();
        this.name = entity.getName();
        this.number = entity.getNumber();
    }

    public BusFavorite toEntity() {
        return BusFavorite.builder()
                .region(region)
                .name(name)
                .number(number)
                .build();
    }
}
