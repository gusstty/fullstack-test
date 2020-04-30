/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustavo.WeathernMusic.endPoint.controller;

import com.gustavo.WeathernMusic.endPoint.service.MusicService;
import com.gustavo.WeathernMusic.model.WeatherWapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gustavo
 */
@RestController
public class MusicController {
    @RequestMapping("/spotify")
    public String spotfy(WeatherWapper weatherWapper){
        
        MusicService ms = new MusicService();
        String recomendations = ms.musicRecomendation("28");
        
        return recomendations;
    }
}
