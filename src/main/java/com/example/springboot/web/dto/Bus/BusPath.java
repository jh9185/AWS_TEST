package com.example.springboot.web.dto.Bus;

import org.json.simple.JSONObject;

public class BusPath {
    Long no;
    double gpsX;
    double gpsY;

    public BusPath(JSONObject jsonObjectBusPos){
        this.no = (Long) jsonObjectBusPos.get("no");
        this.gpsX = (double) jsonObjectBusPos.get("gpsX");
        this.gpsY = (double) jsonObjectBusPos.get("gpsY");
    }
}
