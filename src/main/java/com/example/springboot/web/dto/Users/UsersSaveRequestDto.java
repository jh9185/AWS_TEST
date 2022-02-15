package com.example.springboot.web.dto.Users;

import com.example.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private Long id;
    private String name;
    private String email;
    private String region;

    @Builder
    public UsersSaveRequestDto(Users entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.region = entity.getRegion();
    }
}
