package weather;

import weather.DarkSkyWeather;
import weather.DefaultWeather;

public class WeatherServiceFactory {

    DarkSkyWeather darkSkyForecast;
    DefaultWeather defaultForecast;

    public WeatherServiceFactory() {
        darkSkyForecast = new DarkSkyWeather();
        defaultForecast = new DefaultWeather();
    }

    public String weatherForecast(String service) {

        if (service.equals("DarkSky")) {
            return darkSkyForecast.getWeather();
        } else {
            return defaultForecast.getWeather();
        }
    }
}