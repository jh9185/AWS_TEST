package com.example.springboot.config.auth.dto;

import com.example.springboot.domain.user.Role;
import com.example.springboot.domain.user.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private String region;
    private Role role;
    private String login;

    public SessionUser(Users user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.region = user.getRegion();
        this.role = user.getRole();
        this.login = user.getLogin();
    }
}
