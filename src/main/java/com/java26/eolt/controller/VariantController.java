package com.java26.eolt.controller;

import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.entity.User;
import com.java26.eolt.myEnum.ModificationReason;
import com.java26.eolt.myEnum.VariantStatus;
import com.java26.eolt.service.VariantHistoryService;
import com.java26.eolt.service.VariantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/variant")
public class VariantController {

    private final VariantService variantService;
    private final VariantHistoryService variantHistoryService;

    @GetMapping
    public String showVariant(Model model,
                              @RequestParam String eoltName) {
        log.info("GetMapping: showVariant");
        List<VariantDto> variantDtoList = variantService.findAllVariants(eoltName);
        model.addAttribute("eoltName", eoltName);
        model.addAttribute("variantDtoList", variantDtoList);
        model.addAttribute("variantDtoForm", new VariantDto());
        return "variant";
    }

    @PostMapping("/add")
    public String createVariant(@Valid VariantDto variantDtoForm,
                                @RequestParam String eoltName) {
        if ((variantDtoForm != null)) {
            log.info("PostMapping:postVariant:createVariant");
            variantService.create(variantDtoForm, eoltName);
            variantHistoryService.create(variantDtoForm, eoltName, ModificationReason.CREATE);
        }
        return "redirect:/variant?eoltName=" + eoltName;
    }

    @PostMapping("/setOKStatus")
    public String setOKStatus(@Valid VariantDto variantDtoForm,
                              @RequestParam String eoltName,
                              @RequestParam String variant) {
        if ((variant != null)) {
            log.info("PostMapping:postVariant:setOKStatus");
            VariantDto variantDto = variantService.findVariant(variant, eoltName);
            variantDto.setVariantStatus(VariantStatus.OK);
            variantService.update(variantDto, eoltName);
            variantHistoryService.create(variantDto, eoltName, ModificationReason.UPDATE);
        }
        return "redirect:/variant?eoltName=" + eoltName;
    }

    @PostMapping("/setNOKStatus")
    public String setNOKStatus(@Valid VariantDto variantDtoForm,
                               @RequestParam String eoltName,
                               @RequestParam String variant) {
        log.info("PostMapping:postVariant:setNOKStatus");



        if (variant != null) {
            VariantDto variantDto = variantService.findVariant(variant, eoltName);
            variantDto.setVariantStatus(VariantStatus.NOK);
            variantService.update(variantDto, eoltName);
            variantHistoryService.create(variantDto, eoltName, ModificationReason.UPDATE);
        }

        return "redirect:/variant?eoltName=" + eoltName;
    }

    @PostMapping("/copy")
    public String copyVariant(RedirectAttributes redirectAttributes,
                              @RequestParam String eoltName,
                              @RequestParam String copyVariant) {
        log.info("PostMapping:postVariant:copyVariant");
        VariantDto variantDto = variantService.findVariant(copyVariant, eoltName);
        redirectAttributes.addFlashAttribute("variantToCopy", variantDto);
        //   model.addAttribute("eoltToCopy",eoltDto);
        return "redirect:/variant/addView?eoltName=" + eoltName;
    }

    @PostMapping("/update")
    public String updateVariant(@Valid VariantDto variantDtoForm,
                                @RequestParam String eoltName,
                                @RequestParam String updateVariant) {
        log.info("PostMapping:updateVariant");
        if (updateVariant != null) {
            variantService.update(variantDtoForm, eoltName);
            variantHistoryService.create(variantDtoForm, eoltName, ModificationReason.UPDATE);
        }
        return "redirect:/variant?eoltName=" + eoltName;
    }

    @PostMapping("/delete")
    public String deleteVariant(@Valid VariantDto variantDtoForm,
                                @RequestParam String eoltName,
                                @RequestParam String deleteVariant) {
        if (deleteVariant != null) {
            log.info("PostMapping:postVariant:deleteVariant");
            variantService.delete(deleteVariant, eoltName);
        }

        return "redirect:/variant?eoltName=" + eoltName;
    }

    @GetMapping("/detailed")
    public String showVariantDetailed(Model model,
                                      @RequestParam String variantName2,
                                      @RequestParam String eoltName) {
        List<VariantDto> variantDtoList = variantHistoryService.findAllModifications(eoltName, variantName2);
        VariantDto variantDto = variantService.findVariant(variantName2, eoltName);
        model.addAttribute("myChoosenVariant", variantName2);
        model.addAttribute("variantDtoForm", variantDto);
        model.addAttribute("eoltName", eoltName);
        model.addAttribute("variantDtoList", variantDtoList);
        log.info("get_detailedVariant");
        return "variant_detailed";
    }

    @GetMapping("/addView")
    public String showAddView(Model model, @RequestParam String eoltName) {
        log.info("Get:Variant:adView");
        VariantDto dtoToCopy = (VariantDto) model.getAttribute("variantToCopy");
        model.addAttribute("variantDtoForm", dtoToCopy == null ? new VariantDto() : dtoToCopy);
        model.addAttribute("eoltName", eoltName);
        return "variant_add";
    }


}

