package com.example.springboot.web.controller.Bus;

import com.example.springboot.service.bus.BusService;
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

}
