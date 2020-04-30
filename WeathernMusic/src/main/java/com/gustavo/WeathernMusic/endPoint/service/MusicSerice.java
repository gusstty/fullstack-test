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
public class MusicSerice {

    private final String GET_REQUEST = "GET";
    private final String HOST_WHEATHER = "https://api.spotify.com";
    private final String ENDPOINT_MUSIC_RECOMENDATION = "/v1/browse/categories/rock";
    private final String API_KEY = "Bearer BQBlMqMKCTQQ4nzcvMH1rVDv1LCS8oiOMKVNsOkgLRDFWwPQTTC6b0oj7Ao35Xtujp9NvzjFBx2M2JGduMxQn8uYDFjdkf3znPaOl_VOS2uep2lubzr00qe2pwWqjO0Dt3PU19kq7dIWrlfsc1XanltdK6QrNxH_rhyZBkWvZek1rRIGdNqIyI7VDrh_AX8WVoEOzMEiq-sL17mgkHu1ASWhgNEgoKy9eBpB1SvqAVm4mgNwlrrXa0yZf5BWrjuNlBtboOMxUfJKOYfwxH7JiT5EQN-1vyhciuTP";

    private String musicRecomendation(String temp) {
        RequestController gr = new RequestController();

        String methodRequest = GET_REQUEST;
        String host = HOST_WHEATHER;
        String endpoint = ENDPOINT_MUSIC_RECOMENDATION;
        String requestReturn = null;
        String param = "";

        float fTemp = Float.parseFloat(temp);

        if (fTemp > 30) {
            param = "rock";
        } else if ((fTemp > 15) && (fTemp < 30)) {
            param = "pop";// Pop
        } else if ((fTemp > 10) && (fTemp < 14)) {
            param = "rock";// Rock
        } else if (fTemp <= 0) {
            param = "classic"; //classical music
        }

        try {
            requestReturn = gr.genericRequest(methodRequest, host, endpoint, param, true, API_KEY);
            temp = parseJsonMusicRequest(requestReturn);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }

    private String parseJsonMusicRequest(String request) throws JSONException {
        JSONObject obj = new JSONObject(request);
        JSONObject main = obj.getJSONObject("main");
        String temp = main.getString("temp");

        return temp;
    }
}
