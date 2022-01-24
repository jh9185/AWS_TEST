package com.example.springboot.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiComponent {
    @Value("${naver-map-key}")
    private String mapApiKey;

    @Value("${seoul-data-key}")
    private String busApiKey;

    public String getMapApiKey() {
        return mapApiKey;
    }

    public String getBusApiKey() {
        return busApiKey;
    }
}
