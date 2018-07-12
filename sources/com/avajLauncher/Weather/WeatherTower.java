package sources.com.avajLauncher.Weather;

import sources.com.avajLauncher.Simulator.Vehicles.Coordinates;

public class WeatherTower extends Tower{
    
    public String getWeather(Coordinates coordinates){
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }

    public void changeWeather(){
        conditionsChanged();
    }
}