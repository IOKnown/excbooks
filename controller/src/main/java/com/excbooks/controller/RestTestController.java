package com.excbooks.controller;

import org.apache.log4j.BasicConfigurator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {
    {
        BasicConfigurator.configure();
    }

    @RequestMapping(value = "/api")
    @ResponseBody
    public String main(){
        return "hello spring security";
    }

    @RequestMapping(value = "/apipi")
    @ResponseBody
    public String main1(){
        return "hello spring security?1";
    }
}
