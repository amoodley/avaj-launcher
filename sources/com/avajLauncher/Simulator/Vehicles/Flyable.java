package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Weather.WeatherTower;

public interface Flyable{
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}