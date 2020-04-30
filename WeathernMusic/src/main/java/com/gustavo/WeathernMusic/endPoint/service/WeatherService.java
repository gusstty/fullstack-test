/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustavo.WeathernMusic.endPoint.service;

import com.fasterxml.jackson.core.JsonParser;
import com.gustavo.WeathernMusic.model.Address;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 *
 * @author Gustavo
 */
public class WeatherService {

    private final String POST_REQUEST = "POST";
    private final String GET_REQUEST = "GET";
    private final String HOST_WHEATHER = "http://api.openweathermap.org";
    private final String ENDPOINT_CURRENT_WEATHER = "data/2.5/weather";
    private final String API_KEY = "eea056e0bc3ca68acfa330a8407321b2";

    public String apiWeatherRequestGetCurrentTemperature(Address address) {
        RequestController gr = new RequestController();

        String methodRequest = GET_REQUEST;
        String host = HOST_WHEATHER;
        String endpoint = ENDPOINT_CURRENT_WEATHER;
        String requestReturn = null;
        String temp= null;

        String param = "?q=".concat(address.getCity().concat(",").concat(address.getState()).concat(",")
                .concat(address.getCountry().concat("&units=metric")));
        param += "&appid=".concat(API_KEY);

        try {
            requestReturn = gr.genericRequest(methodRequest, host, endpoint, param, false, null);
            temp = parseJsonWeatherRequest(requestReturn);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
    }
    
    private String parseJsonWeatherRequest(String request) throws JSONException{
        JSONObject obj = new JSONObject(request);
        JSONObject main = obj.getJSONObject("main");
        String temp = main.getString("temp");
        
        return temp;
    } 

}
