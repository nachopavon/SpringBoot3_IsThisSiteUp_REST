package com.nachopavon.helloworldapp.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IsThisSiteUp {
    private final String IS_SITE_UP = "The site is up!";
    private final String IS_SITE_DOWN = "This site is down!";
    private final String INCORRECT_URL = "The URL is incorrect!";

    //Make a rest controller that returns a string
    @GetMapping("/check")
    public String getURLStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj= new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory != 2) {
                returnMessage = IS_SITE_DOWN;
            } else {
                returnMessage = IS_SITE_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = IS_SITE_DOWN;
        }
        return returnMessage;
    }
    
}
