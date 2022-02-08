package com.example.springboot.web.dto.Bus;

import org.json.simple.JSONObject;

public class BusPosDto {
    String plainNo;
    double gpsX;
    double gpsY;

    public BusPosDto(JSONObject jsonObjectBusPos){
        this.plainNo = String.valueOf(jsonObjectBusPos.get("plainNo"));
        this.gpsX = (double) jsonObjectBusPos.get("gpsX");
        this.gpsY = (double) jsonObjectBusPos.get("gpsY");
    }
}
