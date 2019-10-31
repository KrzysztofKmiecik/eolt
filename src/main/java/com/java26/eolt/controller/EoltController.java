package com.java26.eolt.controller;

import com.java26.eolt.dto.EoltDto;
import com.java26.eolt.service.EoltService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/eolt")
public class EoltController {

    private final EoltService eoltService;

    @GetMapping
    public String showEolt(Model model) {
        log.info("GetMapping: showEolt");
        List<EoltDto> eoltDtoList = eoltService.findAll();
        model.addAttribute("eoltDtoList", eoltDtoList);
        model.addAttribute("eoltDtoForm", new EoltDto());
        return "eolt";
    }


    @PostMapping
    public String postEolt(@Valid EoltDto eoltDtoForm, @RequestParam(required = false) String deleteEolt) {
        log.info("PostMapping:postEolt");
        boolean flag = false;
        if (deleteEolt != null) {
            flag = true;
            eoltService.delete(deleteEolt);
        }
        if ((eoltDtoForm != null) && (!flag)) {
            eoltService.create(eoltDtoForm);
        }
        return "redirect:eolt";
    }

    @GetMapping("/detailed")
    public String showEoltDetailed(Model model,@RequestParam String eoltName2 ) {
        model.addAttribute("myChoosenEoltName",eoltName2);
        log.info("get_detailed");
        return "eolt_detailed";
    }



}

