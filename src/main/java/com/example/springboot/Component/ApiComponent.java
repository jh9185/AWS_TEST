package com.example.springboot.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiComponent {
    @Value("${naver-map-key}")
    private String mapApiKey;

    @Value("${seoul-data-key}")
    private String busApiKey;

    @Value("http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?")
    private String busApiSeoulRoutePath;

    @Value("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll?")
    private String busApiSeoulArrInfoPath;

    @Value("http://ws.bus.go.kr/api/rest/busRouteInfo/getRoutePath?")
    private String busApiSeoulAllRoutePath;

    @Value("http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?")
    private String busApiSeoulBusPosPath;

    @Value("http://apis.data.go.kr/6410000/busrouteservice/getBusRouteStationList?")
    private String busApiGGDRoutePath;

    @Value("http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem?")
    private String busApiGGDArrInfoPath;

    @Value("http://apis.data.go.kr/6410000/busrouteservice/getBusRouteLineList?")
    private String busApiGGDAllRoutePath;

    @Value("http://apis.data.go.kr/6410000/buslocationservice/getBusLocationList?")
    private String busApiGGDBusPosPath;

    public String getMapApiKey() {
        return mapApiKey;
    }

    public String getBusApiKey() {
        return busApiKey;
    }

    public String getBusApiSeoulRoutePath() {
        return busApiSeoulRoutePath;
    }

    public String getBusApiSeoulArrInfoPath() {
        return busApiSeoulArrInfoPath;
    }

    public String getBusApiSeoulAllRoutePath() {
        return busApiSeoulAllRoutePath;
    }

    public String getBusApiSeoulBusPosPath() {
        return busApiSeoulBusPosPath;
    }

    public String getBusApiGGDRoutePath() {
        return busApiGGDRoutePath;
    }

    public String getBusApiGGDArrInfoPath() {
        return busApiGGDArrInfoPath;
    }
    public String getBusApiGGDAllRoutePath() {
        return busApiGGDAllRoutePath;
    }

    public String getBusApiGGDBusPosPath() {
        return busApiGGDBusPosPath;
    }
}
