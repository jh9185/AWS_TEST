package com.example.springboot.web.controller.Bus;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.bus.BusService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class BusController {
    private final BusService busService;

    @GetMapping("/bus/{busrouteid}")
    public String busSearch(@PathVariable Long busrouteid, Model model) throws IOException {
        busService.busStationLoadData(busrouteid);
        return "/";
    }

    @GetMapping("/bus/update")
    public String busUpdate(@LoginUser SessionUser user) throws IOException {
        try{
            if(user.getEmail().equals("rnjswogus@gmail.com")){
                busService.readBusNumber();
            }
            else{
                return "권한이 없습니다";
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return String.valueOf(e);
        }
        return "/";
    }
}
