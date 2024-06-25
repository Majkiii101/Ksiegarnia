package pl.zajecia.backendproject.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String API_KEY = "69020ee5934952d9bfbd838ef3f6b3f9";
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=Warsaw&appid=" + API_KEY + "&units=metric";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeather() {
        return restTemplate.getForObject(API_URL, String.class);
    }

}
