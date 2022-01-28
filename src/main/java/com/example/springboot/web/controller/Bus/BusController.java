package com.example.springboot.web.controller.Bus;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.bus.BusService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

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
        return "/";
    }

    @SuppressWarnings("resource")
    @RequestMapping(value ="/bus/fileDBUpload", method=RequestMethod.POST)
    public String insertUploadFile(MultipartHttpServletRequest request) throws Exception {
        try {
            MultipartFile file = null;
            Iterator<String> mIterator = request.getFileNames();
            if(mIterator.hasNext()) {
                file = request.getFile(mIterator.next());
            }
            // 엑셀파일 열기 (엑셀버전 2007 이상일때, 오픈방법)
            OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
            XSSFWorkbook wb = new XSSFWorkbook(opcPackage);

            busService.readBusNumber(wb);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/";
    }
}
