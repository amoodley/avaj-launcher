package sources.com.avajLauncher.Weather;

import sources.com.avajLauncher.Simulator.Vehicles.*;
import sources.com.avajLauncher.Weather.*;

public class WeatherTower extends Tower{
    
    public String getWeather(Coordinates coordinates){
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }

    public void changeWeather(){
        conditionsChanged();
    }
}