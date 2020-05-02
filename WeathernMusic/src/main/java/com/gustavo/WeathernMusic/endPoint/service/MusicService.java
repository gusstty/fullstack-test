package com.gustavo.WeathernMusic.endPoint.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 *
 * @author Gustavo
 */
public class MusicService {

    private final String GET_REQUEST = "GET";
    private final String HOST_WHEATHER = "https://api.spotify.com";
    private final String ENDPOINT_MUSIC_RECOMENDATION = "v1/browse/categories";
    private final String API_KEY = "BQDhUiS7V7yvzGNy20uiXpkuO7AoReN5zWEZnrVO4QTxz2KMhyjpptat8-6ECKfyudmq_fiZr16vaQN3hw0LvaanAZiKpaTnd8wXjUeoyCQU6kA-jfQmqmAQDIisq5Zzw7mTHDHA0IftoIjwxCuBATsXGzULB9ORGc1K_ZzUILZzrLznhL7LqhvmzXG3lkI_XGi3ch2xHxJ-6_il3CY5VRp4WOTGDECBMaID1fbY7ZnYBtficAf3jnGdLSFUEIbFfeXCCG2dIKZEECAsD9DW08KFIq7-1xxo-p6t";

    public String musicRecomendation(String temp) {
        RequestService gr = new RequestService();

        String methodRequest = GET_REQUEST;
        String host = HOST_WHEATHER;
        String endpoint = ENDPOINT_MUSIC_RECOMENDATION;
        String requestReturn = null;
        String param = "";
        String musicRecomendation = null;

        float fTemp = Float.parseFloat(temp);

        if (fTemp > 30) {
            param = "/party";
        } else if ((fTemp > 15) && (fTemp < 30)) {
            param = "/pop";// Pop
        } else if ((fTemp > 10) && (fTemp < 14)) {
            param = "/rock";// Rock
        } else if (fTemp <= 0) {
            param = "/classic"; //classical music
        }

        try {
            requestReturn = gr.genericRequest(methodRequest, host, endpoint, param, true, API_KEY);
            musicRecomendation = parseJsonMusicRequest(requestReturn);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return musicRecomendation;
    }

    private String parseJsonMusicRequest(String request) throws JSONException {
        JSONObject obj = new JSONObject(request);
        //JSONObject main = obj.getJSONObject("href");
        String temp = obj.getString("href");

        return temp;
    }
}
