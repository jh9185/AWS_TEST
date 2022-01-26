package com.example.springboot.web.dto.Users;

import com.example.springboot.domain.user.Users;
import lombok.Getter;

@Getter
public class UsersResponseDto {
    private Long id;
    private String name;
    private String email;
    private String region;

    public UsersResponseDto(Users entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.region = entity.getRegion();
    }
}
