package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Weather.*;
import sources.com.avajLauncher.Simulator.*;

public interface Flyable{
    
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}