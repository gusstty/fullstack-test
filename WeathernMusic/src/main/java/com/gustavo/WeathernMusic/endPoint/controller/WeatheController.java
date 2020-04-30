package com.gustavo.WeathernMusic.endPoint.controller;

import com.gustavo.WeathernMusic.endPoint.service.MusicSerice;
import com.gustavo.WeathernMusic.endPoint.service.WeatherService;
import com.gustavo.WeathernMusic.model.Address;
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
    @RequestMapping("/weather")
    @ResponseBody
    public String weather(@RequestParam(required = true) String city,
                          @RequestParam(required = true) String state,
                            @RequestParam(required = true) String country){        
        WeatherService ws = new WeatherService();
        
        Address address = new Address();
        
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        String teste = ws.apiWeatherRequestGetCurrentTemperature(address);
        
        return teste; 
    }
}
