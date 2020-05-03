package com.gustavo.WeathernMusic.endPoint.controller;

import com.gustavo.WeathernMusic.endPoint.service.MusicService;
import com.gustavo.WeathernMusic.endPoint.service.WeatherService;
import com.gustavo.WeathernMusic.model.Address;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gustavo
 */
@RestController
public class WeatheController {
    @CrossOrigin
    @RequestMapping("/weather")
    @ResponseBody
    public String weather(@RequestParam(required = true) String city,
            @RequestParam(required = true) String state,
            @RequestParam(required = true) String country) {
        WeatherService ws = new WeatherService();
        MusicService ms = new MusicService();
        Address address = new Address();

        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        String currentTemp = ws.apiWeatherRequestGetCurrentTemperature(address);
        String musicRecomendation = ms.musicRecomendation(currentTemp);
        
        //return currentTemp;
        return musicRecomendation;
    }
}
