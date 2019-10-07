package weather;

import weather.DarkSkyWeather;
import weather.DefaultWeather;

public class WeatherServiceFactory {

    DarkSkyWeather darkSkyService;
    DefaultWeather defaultService;

    public WeatherServiceFactory() {
        darkSkyService = new DarkSkyWeather();
        defaultService = new DefaultWeather();
    }

    public WeatherService getWeatherService(String service) {

        if (service.equals("DarkSky")) {
            return darkSkyService;
        } else {
            return defaultService;
        }
    }
}