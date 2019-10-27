package com.java26.eolt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class myController {

    @GetMapping
    public String  showHello(){
        return "hello";
    }
}
