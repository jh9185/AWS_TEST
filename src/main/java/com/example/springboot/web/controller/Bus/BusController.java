package com.example.springboot.web.controller.Bus;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.domain.bus.BusFavorite;
import com.example.springboot.service.bus.BusService;
import com.example.springboot.web.dto.Bus.BusFavoriteSaveRequestDto;
import com.example.springboot.web.dto.Bus.BusPathDto;
import com.example.springboot.web.dto.Bus.BusPosDto;
import com.example.springboot.web.dto.Bus.BusStationInfoListDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
        JSONArray jsonArrayBusPath = busService.BusStationLoadPathData(busrouteid);
        JSONArray jsonArrayBusPos = busService.BusLoadPosData(busrouteid);

        List<BusStationInfoListDto> busStationInfoListDtoList = new ArrayList<BusStationInfoListDto>();
        List<BusPathDto> busPathList = new ArrayList<BusPathDto>();
        List<BusPosDto> busPosDtoList = new ArrayList<BusPosDto>();

        for (int i = 0; i < jsonArrayStation.size(); i++) {
            JSONObject jsonStation = (JSONObject) jsonArrayStation.get(i);

            for (int j = 0; j < jsonArrayArrive.size(); j++) {
                JSONObject jsonArrive = (JSONObject) jsonArrayArrive.get(j);
                if (jsonStation.get("arsId").equals(jsonArrive.get("arsId"))) {

                    busStationInfoListDtoList.add(new BusStationInfoListDto(jsonStation, jsonArrive));
                    break;
                }
            }
        }

        for (int i = 0; i < jsonArrayBusPath.size(); i++) {
            busPathList.add((new BusPathDto((JSONObject) jsonArrayBusPath.get(i))));
        }

        for (int i = 0; i < jsonArrayBusPos.size(); i++) {
            busPosDtoList.add((new BusPosDto((JSONObject) jsonArrayBusPos.get(i))));
        }

        model.addAttribute("mapCenter", busStationInfoListDtoList.get(0));
        model.addAttribute("busStationList", busStationInfoListDtoList);
        model.addAttribute("busPathList", busPathList);
        model.addAttribute("busPosList", busPosDtoList);

        return "map/mapView";
    }

    @GetMapping("/bus/update")
    public String busUpdate(@LoginUser SessionUser user) throws IOException {
        return "/";
    }

    @PostMapping("/bus/favorite/save")
    @ResponseBody
    public Long save(@LoginUser SessionUser user, @RequestBody BusFavoriteSaveRequestDto requestDto) {
        BusFavorite busFavorite = new BusFavorite(
                requestDto.getRegion(),
                requestDto.getName(),
                requestDto.getNumber(),
                user.getEmail());

        return busService.favoriteSave(busFavorite);
    }

    @DeleteMapping("/bus/favorite/delete")
    @ResponseBody
    public Long delete(@LoginUser SessionUser user, @RequestBody BusFavoriteSaveRequestDto requestDto) {
        List<BusFavorite> busFavoriteList = busService.busFavorliteFind(user.getEmail());
        BusFavorite busFavorite = new BusFavorite();

        for(int i=0; i<busFavoriteList.size(); i++){
            if(busFavoriteList.get(i).getName().equals(requestDto.getName())){
                busFavorite = busFavoriteList.get(i);
                busService.favoriteDelete(busFavorite);
            }
        }
        return busFavorite.getId();
    }



    @PostMapping(value = "/bus/fileDBUpload")
    @ResponseBody
    public HttpStatus insertUploadFile(@LoginUser SessionUser user, MultipartHttpServletRequest request) throws Exception {
        try {
            if(user.getRole().getKey() == "ROLE_MASTER" ){
                MultipartFile file = null;
                Iterator<String> mIterator = request.getFileNames();
                if (mIterator.hasNext()) {
                    file = request.getFile(mIterator.next());
                }

                busService.readBusNumber(file.getInputStream());
                return HttpStatus.OK;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return HttpStatus.BAD_REQUEST;
    }
}