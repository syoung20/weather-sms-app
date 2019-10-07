import weather.WeatherServiceFactory;
import weather.WeatherService;

public class Main {

    public static void main(String args[]) {

        while(true) {
            //first argument is weather forecasting service to use
            //second argument is phone number to message
            String service = args[0];
            String number = args[1];

            //get and send weather information every morning at 6:00am
            String currentTime = java.time.LocalTime.now().toString();
            StringBuilder time = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                time.append(currentTime.charAt(i));
            }
            if (time.toString().equals("06:00")) {
                System.out.println(time.toString());
                runApp(service, number);
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }

    public static void runApp(String service, String number){

        WeatherServiceFactory factory = new WeatherServiceFactory();
        WeatherService weather = factory.getWeatherService(service);
        String forecast = weather.getWeather();
        SmsSender.sendSMS(forecast, number);
    }
}