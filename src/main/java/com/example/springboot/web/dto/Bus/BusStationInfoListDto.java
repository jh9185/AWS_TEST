package com.example.springboot.web.dto.Bus;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Getter
public class BusStationInfoListDto {

    Long seq;
    String arsId;
    String stStationNm;
    String stationNo;
    double gpsX;
    double gpsY;
    String busRouteNm;
    Long busRouteId;
    String transYn;
    String arrmsg1;
    String arrmsg2;
    String stationNm;
    String plainNo1;
    String plainNo2;

    public BusStationInfoListDto(JSONObject jsonObjectStation, JSONObject jsonObjectArrive){
        this.seq = (Long) jsonObjectStation.get("seq");
        this.arsId = String.valueOf(jsonObjectStation.get("arsId"));
        this.stStationNm = String.valueOf(jsonObjectStation.get("stationNm"));
        this.stationNo = String.valueOf(jsonObjectStation.get("stationNo"));
        this.gpsX = (double) jsonObjectStation.get("gpsX");
        this.gpsY = (double) jsonObjectStation.get("gpsY");
        this.busRouteNm = String.valueOf(jsonObjectStation.get("busRouteNm"));
        this.busRouteId = (Long) jsonObjectStation.get("busRouteId");
        this.transYn = String.valueOf(jsonObjectStation.get("transYn"));

        this.arrmsg1 = String.valueOf(jsonObjectArrive.get("arrmsg1"));
        this.arrmsg2 = String.valueOf(jsonObjectArrive.get("arrmsg2"));
        this.stationNm = String.valueOf(jsonObjectArrive.get("stNm"));
        this.plainNo1 = String.valueOf(jsonObjectArrive.get("plainNo1"));
        this.plainNo2 = String.valueOf(jsonObjectArrive.get("plainNo2"));
    }
}
