package com.java26.eolt.controller;

import com.java26.eolt.dto.EoltDto;
import com.java26.eolt.service.EoltService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyRestController {

    private final EoltService eoltService;

    @RequestMapping("/api/eolt")
    public List<EoltDto> getEolts() {
        return eoltService.findAll();
    }
}
