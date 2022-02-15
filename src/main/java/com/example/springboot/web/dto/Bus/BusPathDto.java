package com.example.springboot.web.dto.Bus;

import org.json.simple.JSONObject;

public class BusPathDto {
    Long no;
    double gpsX;
    double gpsY;

    public BusPathDto(JSONObject jsonObjectBusPos){
        this.no = (Long) jsonObjectBusPos.get("no");
        this.gpsX = (double) jsonObjectBusPos.get("gpsX");
        this.gpsY = (double) jsonObjectBusPos.get("gpsY");
    }

    public BusPathDto(Long no, double gpsX, double gpsY){
        this.no = no;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
    }

}
