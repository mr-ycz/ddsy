package com.ycz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/other")
public class Other {

    @RequestMapping("/header")
    public String header(){
        return "header";
    }

}
