package com.example.springboot.config.auth;

import com.example.springboot.domain.user.Role;
import com.example.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String region;
    private String login;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String picture, String login)
    {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.region = "";
        this.login = login;
    }

    public static OAuthAttributes of (String registrationId, String userNameAttributeName,
                                      Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id",attributes);
        }
        else if("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .login("google")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .login("naver")
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        profile.put("email", kakaoAccount.get("email"));
        profile.put("username", profile.get("nickname"));

        profile.put("id", attributes.get("id"));

        return OAuthAttributes.builder()
                .name((String) profile.get("username"))
                .email((String) profile.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .login("kakao")
                .attributes(profile)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .region(region)
                .login(login)
                .build();
    }
}
