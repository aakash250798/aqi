package com.aqi.aqiApp.controller;


import com.aqi.aqiApp.service.AqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AqiController {


    @Autowired
    AqiService aqiService;

    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping("/weather/{longitute}/{latitude}")
    public String aqi(@PathVariable Double longitute, @PathVariable Double latitude) {

        return aqiService.aqiResponse(longitute,latitude);


    }
    @GetMapping("/weather/{city}")
    public String aqi2(@PathVariable String city) {

        return aqiService.aqiResponse(city);


    }



}



