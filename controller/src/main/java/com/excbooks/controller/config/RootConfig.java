package com.excbooks.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import({ServletConfig.class, DaoConfig.class})
@ComponentScan(basePackages = "com.excbooks.controller")
public class RootConfig{
    RootConfig(){

    }
}
