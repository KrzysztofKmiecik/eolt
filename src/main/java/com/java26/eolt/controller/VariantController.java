package com.java26.eolt.controller;

import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.service.VariantService;
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
@RequestMapping("/variant")
public class VariantController {

    private final VariantService variantService;

    @GetMapping
    public String showVariant(Model model, @RequestParam String dpn) {
        log.info("GetMapping: showVariant");
        List<VariantDto> variantDtoList = variantService.findAllVariants();
        model.addAttribute("dpn",dpn);
        model.addAttribute("variantDtoList", variantDtoList);
        model.addAttribute("variantDtoForm", new VariantDto());
        return "variant";
    }


    @PostMapping
    public String postVariant(@Valid VariantDto variantDtoForm, @RequestParam(required = false) String deleteVariant) {
        log.info("PostMapping:postVariant");
        boolean flag = false;
        if (deleteVariant != null) {
            flag = true;
            variantService.delete(deleteVariant);
        }
        if ((variantDtoForm != null) && (!flag)) {
            variantService.create(variantDtoForm);
        }
        return "redirect:variant";
    }

    @GetMapping("/detailedVariant")
    public String showVariantDetailed(Model model, @RequestParam String variantName2) {
        model.addAttribute("myChoosenVariant", variantName2);
        log.info("get_detailedVariant");
        return "variant_detailed";
    }


}

