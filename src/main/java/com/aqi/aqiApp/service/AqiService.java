package com.aqi.aqiApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class AqiService {

    @Autowired
    RestTemplate restTemplate;

    public String aqiResponse (Double longitute, Double latitude){

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/.env")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String apiKey = props.getProperty("API_KEY");
        String url = String.format(
                "http://api.openweathermap.org/data/2.5/air_pollution?lat=%s&lon=%s&appid=%s",
                longitute, latitude,apiKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }

    public String aqiResponse (String city){

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/.env")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String apiKey = props.getProperty("API_KEY");

        String url = String.format
                ("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s",city,apiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        Map<String,String> map = (Map<String, String>) response.getBody().get(0);
            url = String.format(
                "http://api.openweathermap.org/data/2.5/air_pollution?lat=%s&lon=%s&appid=%s",
                map.get("lat"), map.get("lon"),apiKey);
         headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response1 = restTemplate.getForEntity(url, String.class);

        return response1.getBody();
    }
}
