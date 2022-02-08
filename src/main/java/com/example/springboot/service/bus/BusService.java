package com.example.springboot.service.bus;

import com.example.springboot.Component.ApiComponent;
import com.example.springboot.domain.bus.Bus;
import com.example.springboot.domain.bus.BusRepository;
import com.example.springboot.web.dto.Bus.BusListResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;

import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.transaction.annotation.Transactional;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//            for (int i =0; i<itemList.size(); i++){
//                  JSONObject detailInfo = (JSONObject)itemList.get(i);
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
//            }
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray itemList = new JSONArray();
        return itemList;
    }
    // 정류장 도착 정보 데이터 가져오기
    public JSONArray BusStationLoadArriveData(Long busRouteId){
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll?" +
                    "serviceKey=" + ApiKey.getBusApiKey());
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + busRouteId); /**/
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

//            for (int i =0; i<BusStationVos.size(); i++){
//                BusStationVo busStationVo = BusStationVos.get(i);
//
//                JSONObject detailInfo = (JSONObject)itemList.get(i);
//                System.out.println("도착정보  : " + detailInfo.get("arrmsg1"));
//                System.out.println("도착정보2  : " + detailInfo.get("arrmsg2"));
//                System.out.println("정류장이름  : " + detailInfo.get("stNm"));
//                System.out.println("버스번호  : " + detailInfo.get("plainNo1"));
//                System.out.println("버스번호2  : " + detailInfo.get("plainNo2"));
//                if(detailInfo.get("stNm").equals(busStationVo.getStStationNm())){
//                    busStationVo.setArrmsg((String) detailInfo.get("arrmsg1"));
//                    busStationVo.setArrmsg2((String) detailInfo.get("arrmsg2"));
//                    busStationVo.setPlainNo1((String) detailInfo.get("plainNo1"));
//                    busStationVo.setPlainNo2((String) detailInfo.get("plainNo2"));
//                }
//            }

            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray itemList = new JSONArray();
        return itemList;
    }

    public JSONArray BusStationLoadPathData(Long busRouteId) {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getRoutePath?" +
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
//            for (int i =0; i<itemList.size(); i++){
//                Point2D pointPath = new Point2D.Double();
//
//                JSONObject detailInfo = (JSONObject)itemList.get(i);
////                System.out.println("위치 x  : " + detailInfo.get("gpsX"));
////                System.out.println("위치 y  : " + detailInfo.get("gpsY"));
//                pointPath.setLocation((Double) detailInfo.get("gpsX"), (Double) detailInfo.get("gpsY"));
//
//                pathPointList.add(pointPath);
//            }
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray itemList = new JSONArray();
        return itemList;
    }

    //실시간 버스 위치 가져오기
    public JSONArray BusLoadPosData(Long busRouteId){
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?" +
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

//            for (int i =0; i<itemList.size(); i++){
//                VehId vehId = new VehId("", 0, 0);
//
//                JSONObject detailInfo = (JSONObject)itemList.get(i);
//
//                vehId.setPlainNo((String) detailInfo.get("plainNo"));
//                vehId.setPosX((Double) detailInfo.get("gpsX"));
//                vehId.setPosY((Double) detailInfo.get("gpsY"));
//
//                VehIdList.add(vehId);
//            }

            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray itemList = new JSONArray();
        return itemList;
    }
    // csv file load
    public void readBusNumber(InputStream inputStream) throws IOException {
//        FileInputStream fis=new FileInputStream("C:\\Users\\KJH\\Downloads\\서울시 버스노선ID 정보(202105210).xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(inputStream);

        String region = "서울";
        List<Bus> busList = new ArrayList<Bus>();
        int rowindex=0;
        int columnindex=0;
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        XSSFSheet sheet=workbook.getSheetAt(0);
        //행의 수
        int rows=sheet.getPhysicalNumberOfRows();

        for(rowindex=1;rowindex<rows;rowindex++){
            String name = "";
            String number = "";
            //행을읽는다
            XSSFRow row=sheet.getRow(rowindex);

            if(row !=null){
                //셀의 수
                int cells=row.getPhysicalNumberOfCells();
                for(columnindex=0;columnindex<=cells;columnindex++){

                    //셀값을 읽는다
                    XSSFCell cell=row.getCell(columnindex);
                    String value="";
                    //셀이 빈값일경우를 위한 널체크
                    if(cell==null){
                        continue;
                    }else{
                        //타입별로 내용 읽기
                        switch (cell.getCellType()){
                            case FORMULA:
                                value=cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value=cell.getNumericCellValue()+"";
                                break;
                            case STRING:
                                value=cell.getStringCellValue()+"";
                                if(value.equals("ROUTER_ID") || value.equals("노선명"))
                                    continue;
                                break;
                            case BLANK:
                                value=cell.getBooleanCellValue()+"";
                                break;
                            case ERROR:
                                value=cell.getErrorCellValue()+"";
                                break;
                        }
                        switch(columnindex){
                            case 0:
                                number = value;
                                break;
                            case 1:
                                name = value;

                                Bus bus = new Bus(region, name, number);
                                busList.add(bus);
                                break;
                        }
                    }
                }
            }
        }
        busRepository.saveAll(busList);
        return;
    }

    @Transactional(readOnly = true)
    public List<BusListResponseDto> findAllDesc() {
        return busRepository.findAllDesc().stream()
                .map(BusListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteByRegion(String region) {
        busRepository.deleteByRegion(region);
    }
}
