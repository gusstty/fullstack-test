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
    private final String API_KEY ="BQAQMTftp3r4owRo-2BJ0c6na8vGYKTL1M4-miHmsPz7HbUIG5BofJAo_dmKPUvNE9sZuAySQjkFsQW035TJjzJN8XAhEfRdg4uT8LO3fhXbGVErRRb6Nsv-P-uzY3y8Aw_r_POp765iSrBmTiUet07z-wSgLG767j1ls6mgk_8URYHBxPMmIsSQ8axIhBfrijFydEOUD9-oxpVPEdJ2aki7rjYyn2QKE4zXipD-ycDBhzKmuJYe99zxiti6FUDS_rgQBP3xdn5GJ6WMpgiszDXoLrUwhRArC1fS";

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
            return ex.toString();
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
