package com.example.springboot.domain.user;

import com.example.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String region;

    @Column
    private String login;

    @Builder
    public Users(String name, String email, String picture, Role role, String region, String login) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.region = region;
        this.login = login;
    }

    public Users update(String name, String picture, String login) {
        this.name = name;
        this.picture = picture;
        this.login = login;

        return this;
    }

    public void updateRegion(String region){
        this.region = region;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
