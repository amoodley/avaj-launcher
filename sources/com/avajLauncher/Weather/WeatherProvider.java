package sources.com.avajLauncher.Weather;

import sources.com.avajLauncher.Simulator.Vehicles.Coordinates;

public class WeatherProvider{
    private static WeatherProvider      weatherProvider;
    private static String[]             weather = {"SUN", "RAIN", "FOG", "SNOW"};
    private Integer                     algorithm = 0;

    private WeatherProvider(){

    }

    public static WeatherProvider getProvider(){
        return (weatherProvider);
    }

    public String getCurrentWeather(Coordinates coordinates){
        algorithm = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        return (weather[algorithm % 4]);
    }
}