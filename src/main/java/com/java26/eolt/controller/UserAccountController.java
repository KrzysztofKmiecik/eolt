package com.java26.eolt.controller;

import com.java26.eolt.dto.UserDto;
import com.java26.eolt.entity.User;
import com.java26.eolt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class UserAccountController {

    private final UserService userService;

    @GetMapping
    public String account(Model model, @AuthenticationPrincipal User user) {
        UserDto dto = userService.findByUsername(user.getUsername());
        model.addAttribute("userForm", dto);
        return "account";
    }

    @PostMapping
    public String updateAccount(@ModelAttribute UserDto userForm, @AuthenticationPrincipal User user) {
        userService.update(user.getUsername(), userForm);
        return "redirect:/account";
    }
}
