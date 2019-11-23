package com.java26.eolt.controller;

import com.java26.eolt.dto.EoltDto;
import com.java26.eolt.dto.SearchDto;
import com.java26.eolt.service.EoltService;
import com.java26.eolt.service.VariantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/eolt")
public class EoltController {

    private final EoltService eoltService;
    private final VariantService variantService;

    @GetMapping
    public String showEolt(Model model) {
        EoltDto dtoToCopy = (EoltDto) model.getAttribute("eoltToCopy");
        log.info("GetMapping: showEolt");
        List<EoltDto> eoltDtoList = eoltService.findAll();
        model.addAttribute("eoltDtoList", eoltDtoList);
        model.addAttribute("eoltDtoForm", dtoToCopy == null ? new EoltDto() : dtoToCopy);
        return "eolt";
    }

    @PostMapping("/add")
    public String createEolt(@Valid EoltDto eoltDtoForm) {
        log.info("PostMapping:postEolt:add");
        if ((eoltDtoForm != null)) {
            eoltService.create(eoltDtoForm);
        }
        return "redirect:/eolt";
    }

    @PostMapping("/copy")
    public String copyEolt(RedirectAttributes redirectAttributes,
                           @RequestParam(required = false) String copyEolt) {
        log.info("PostMapping:postEolt:copy");
        EoltDto eoltDto = eoltService.findByName(copyEolt);
        redirectAttributes.addFlashAttribute("eoltToCopy", eoltDto);
        //   model.addAttribute("eoltToCopy",eoltDto);
        return "redirect:/eolt/addView";
    }

    @PostMapping("/delete")
    public String deleteEolt(@RequestParam(required = false) String deleteEolt) {
        log.info("PostMapping:postEolt:delete");

        if (deleteEolt != null) {
            variantService.deleteAllVariantsFromEoltName(deleteEolt);
            eoltService.delete(deleteEolt);
        }
        return "redirect:/eolt";
    }

    @PostMapping("/update")
    public String updateEolt(@Valid EoltDto eoltDtoForm,
                             @RequestParam(required = false) String updateEolt) {
        log.info("PostMapping:update");
        if (updateEolt != null) {
            eoltService.update(eoltDtoForm);
        }
        return "redirect:/eolt";
    }

    @GetMapping("/detailed")
    public String showEoltDetailed(Model model, @RequestParam String eoltName2) {
        EoltDto eoltDto = eoltService.findByName(eoltName2);
        model.addAttribute("eoltDtoForm", eoltDto);
        log.info("get_detailed");
        return "eolt_detailed";
    }

    @GetMapping("/addView")
    public String showAddView(Model model,@RequestParam(required = false) String eoltName2){
        EoltDto dtoToCopy = (EoltDto) model.getAttribute("eoltToCopy");
        model.addAttribute("eoltDtoForm", dtoToCopy == null ? new EoltDto() : dtoToCopy);
        return "eolt_add";
    }


    @GetMapping("/search")
    public String showSearchEolt(Model model) {
        SearchDto searchDto = (SearchDto) model.getAttribute("searchStringAtribute");
        if (searchDto == null) {
            searchDto = new SearchDto();
        }
        List<EoltDto> eoltDtos = eoltService.findEoltContaining(searchDto.getSearchString());
        model.addAttribute("searchStringForm", searchDto);
        model.addAttribute("searchStringListForm", eoltDtos);
        log.info("GetMapping: showSearchEolt {}", searchDto.getSearchString());

        return "eolt_search";
    }


    @PostMapping("/search")
    public String FindEolt(RedirectAttributes redirectAttributes, @ModelAttribute("searchDto") SearchDto searchDto) {
        log.info("post_search");
        redirectAttributes.addFlashAttribute("searchStringAtribute", searchDto == null ? new SearchDto() : searchDto);
        return "redirect:/eolt/search";
    }

    @ModelAttribute
    public SearchDto searchDto() {
        return new SearchDto();
    }


}

