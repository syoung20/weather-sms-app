import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Main {

    public static void main(String args[]) {

        while(true) {
            String currentTime = java.time.LocalTime.now().toString();
            StringBuilder time = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                time.append(currentTime.charAt(i));
            }
            if (time.toString().equals("06:00")) {
                System.out.println(time.toString());
                runApp();
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }

    public static void runApp(){
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

        try {
            url = new URL("https://api.darksky.net/forecast/951075d84ade6291a64fff0c6b5cf2a1/42.36883522461989,-72.51499016550456");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Sending 'GET' request to URL: " + url);
            System.out.println("Response code: " + responseCode);
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
            SmsSender.sendSMS("Good morning! " +
                    "Right now it is " + hourSummary + " The current temperature is " + currentTemp + " and feels like " + currentFeel + "." +
                    " It will be " + daySummary + " The high temperature will be " + maxTemp + ", with a low of " + minTemp + ".");


        } catch (IOException | JSONException ex ) {
            ex.printStackTrace();
        }
    }
}