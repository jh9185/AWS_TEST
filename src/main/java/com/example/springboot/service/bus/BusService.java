package com.example.springboot.service.bus;

import com.example.springboot.Component.ApiComponent;
import com.example.springboot.domain.bus.Bus;
import com.example.springboot.domain.bus.BusRepository;
import com.example.springboot.domain.bus.BusStation;
import com.example.springboot.domain.posts.Posts;
import com.example.springboot.web.dto.Bus.BusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Service
public class BusService {
    private final BusRepository busRepository;
    private final ApiComponent ApiKey;
    // 실시간 노선 데이터 가져오기
    public JSONArray busStationLoadData(Long busRouteId) throws IOException {

        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?" +
                    "serviceKey=" + ApiKey.getBusApiKey());
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + busRouteId.toString()); /**/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            //Connect check log
            //System.out.println(sb.toString());

            org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
            String xmlJSONObjString = xmlJSONObj.toString();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(xmlJSONObjString);
            JSONObject busStationInfoResult = (JSONObject)jsonObject.get("ServiceResult");
            JSONObject busStationInfo = (JSONObject)busStationInfoResult.get("msgBody");

            JSONArray itemList = (JSONArray)busStationInfo.get("itemList");
            for (int i =0; i<itemList.size(); i++){
                  JSONObject detailInfo = (JSONObject)itemList.get(i);
//                System.out.println("노선 ID  : " + detailInfo.get("busRouteId"));
//                System.out.println("노선명   : " + detailInfo.get("busRouteNm"));
//                System.out.println("순번     : " + detailInfo.get("seq"));
//                System.out.println("구간 ID  : " + detailInfo.get("section"));
//                System.out.println("정류소 ID  : " + detailInfo.get("station"));
//                System.out.println("정류소 이름 : " + detailInfo.get("stationNm"));
//                System.out.println("정류소 X  : " + detailInfo.get("gpsX"));
//                System.out.println("정류소 Y : " + detailInfo.get("gpsY"));
//                System.out.println("-------------------------------------------");
                //busStation.setBusRouteId((Long) detailInfo.get("busRouteId"));
            }
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray itemList = new JSONArray();
        return itemList;
    }

//    public BusResponseDto findById (Long id) {
//        Bus entity = BusRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
//
//        return new BusResponseDto(entity);
//    }

}
