package com.aqi.aqiApp.controller;


import com.aqi.aqiApp.service.AqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AqiController {

    @Autowired
    AqiService aqiService;

    @RequestMapping("/")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>(" This application is useful for finding AQI of a city using city name, location ..", HttpStatus.OK);
    }

    @GetMapping("/aqi/{longitute}/{latitude}")
    public ResponseEntity<String> aqiByLocation(@PathVariable Double longitute, @PathVariable Double latitude) {
        return aqiService.aqiResponse(longitute,latitude);

    }
    @GetMapping("/aqi/{city}")
    public ResponseEntity<String> aqiByCityName(@PathVariable String city) {
        return aqiService.aqiResponse(city);
    }

}



