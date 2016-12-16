package com.excbooks.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "log-in.html";
    }

    @RequestMapping("/sing-up")
    public String singUp(){
        return "sing-up.html";
    }
}
