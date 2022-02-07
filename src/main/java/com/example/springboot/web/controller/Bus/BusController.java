package com.example.springboot.web.controller.Bus;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.bus.BusService;
import com.example.springboot.web.dto.Bus.BusPath;
import com.example.springboot.web.dto.Bus.BusStationInfoListDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BusController {
    private final BusService busService;

    @GetMapping("/map")
    public String mapView() {
        return "map/mapView";
    }

    @GetMapping("/bus/{busrouteid}")
    public String busStationSearch(@PathVariable Long busrouteid, Model model) throws IOException {
        JSONArray jsonArrayStation = busService.busStationLoadData(busrouteid);
        JSONArray jsonArrayArrive = busService.BusStationLoadArriveData(busrouteid);
        JSONArray jsonArrayBusPos = busService.BusStationLoadPathData(busrouteid);

        List<BusStationInfoListDto> busStationInfoListDtoList = new ArrayList<BusStationInfoListDto>();
        List<BusPath> busPathList = new ArrayList<BusPath>();

        for(int i=0; i<jsonArrayStation.size(); i++){
            JSONObject jsonStation = (JSONObject) jsonArrayStation.get(i);

            for(int j=0; j<jsonArrayArrive.size(); j++){
                JSONObject jsonArrive = (JSONObject) jsonArrayArrive.get(j);
                if (jsonStation.get("arsId").equals(jsonArrive.get("arsId"))) {

                    busStationInfoListDtoList.add(new BusStationInfoListDto(jsonStation, jsonArrive));
                    break;
                }
            }
        }

        for(int i=0; i<jsonArrayBusPos.size(); i++){
            busPathList.add((new BusPath((JSONObject) jsonArrayBusPos.get(i))));
        }

        model.addAttribute("busStationList", busStationInfoListDtoList);
        model.addAttribute("busPathList", busPathList);

        return "map/mapView";
    }

    @GetMapping("/bus/update")
    public String busUpdate(@LoginUser SessionUser user) throws IOException {
        return "/";
    }

    @SuppressWarnings("resource")
    @RequestMapping(value ="/bus/fileDBUpload", method=RequestMethod.POST)
    public String insertUploadFile(@LoginUser SessionUser user, MultipartHttpServletRequest request) throws Exception {
        try {
            if(user.getRole().getKey() == "ROLE_MASTER" ){
                MultipartFile file = null;
                Iterator<String> mIterator = request.getFileNames();
                if(mIterator.hasNext()) {
                    file = request.getFile(mIterator.next());
                }

                busService.readBusNumber(file.getInputStream());
            }
            else{
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
