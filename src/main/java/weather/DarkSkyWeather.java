package weather;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class DarkSkyWeather implements WeatherService {

    public DarkSkyWeather() {}

    public String getWeather() {

        URL url;
        HttpURLConnection con;
        StringBuilder response;
        String longitude;
        String latitude;
        String minTemp;
        String maxTemp;
        String daySummary;
        String hourSummary;
        String currentTemp;
        String currentFeel;
        String summary;

        String to_return;

        try {
            url = new URL("https://api.darksky.net/forecast/57f29fb90c3c68ae704071d376fd4c5f/42.36883522461989,-72.51499016550456");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            String responseMessage = con.getResponseMessage();
            System.out.println("Sending 'GET' request to URL: " + url);
            System.out.println("Response code: " + responseCode);
            System.out.println(responseMessage);
            InputStreamReader is = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(is);
            String line;
            response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            System.out.println(response);
            in.close();
            JSONObject myResponse = new JSONObject(response.toString());
            JSONObject currently = myResponse.getJSONObject("currently");
            JSONObject minutely = myResponse.getJSONObject("minutely");
            JSONObject hourly = myResponse.getJSONObject("hourly");
            JSONObject daily = myResponse.getJSONObject("daily");
            JSONArray days = daily.getJSONArray("data");
            JSONObject currentDay = days.getJSONObject(0);
            currentTemp = currently.get("temperature").toString();
            currentFeel = currently.get("apparentTemperature").toString();
            hourSummary = minutely.get("summary").toString().toLowerCase();
            daySummary = hourly.get("summary").toString().toLowerCase();
            maxTemp = currentDay.get("temperatureMax").toString();
            minTemp = currentDay.get("temperatureMin").toString();
            to_return = "Good morning! " + "Right now it is " + hourSummary + " The current temperature is " + currentTemp +
                    " and feels like " + currentFeel + "." + " It will be " + daySummary + " The high temperature will be " +
                    maxTemp + ", with a low of " + minTemp + ".";

        } catch (IOException | JSONException ex ) {
            ex.printStackTrace();
            to_return = "Unable to fetch weather foreacast";
        }

        return to_return;

    }


}