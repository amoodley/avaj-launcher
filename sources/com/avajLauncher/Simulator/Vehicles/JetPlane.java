package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Simulator.Vehicles.*;
import sources.com.avajLauncher.Weather.*;
import sources.com.avajLauncher.Weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable{

    private WeatherTower        weatherTower;

    public JetPlane(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates);
        String weatherOutput = "";
        String unregisterOutput = "";
        String aircraftName = "JetPlane#" + this.name + "(" + this.id + "): ";
        switch(weather){
            case "SUN":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
                this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                // System.out.printf("JetPlane#%s(%d): SUN: Latitude = %d, Height = %d\n", this.name, this.id, this.coordinates.getLatitude(), this.coordinates.getHeight());
                weatherOutput = aircraftName + "This is hot. Latitude = " + this.coordinates.getLatitude() + ", Height = " + this.coordinates.getHeight() + "\n";
                break;
            case "RAIN":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
                // System.out.printf("JetPlane#%s(%d): RAIN: Latitude = %d\n", this.name, this.id, this.coordinates.getLatitude());
                weatherOutput = aircraftName + "It's raining! Better watch out for lightning. Latitude = " + this.coordinates.getLatitude() + "\n";
                break;
            case "FOG":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
                // System.out.printf("JetPlane#%s(%d): FOG: Latitude = %d\n", this.name, this.id, this.coordinates.getLatitude());
                weatherOutput = aircraftName + "So much fog! I can't see anything. Latitude = " + this.coordinates.getLatitude() + "\n";
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 7);
                // System.out.printf("JetPlane#%s(%d): SNOW: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                weatherOutput = aircraftName + "OMG! Winter is coming! Height = " + this.coordinates.getHeight() + "\n";
                break;
        }
        weatherTower.writeToFile("write", weatherOutput);

        if (this.coordinates.getHeight() > 100){
            this.coordinates.setHeight(100);
        }

        if (this.coordinates.getHeight() <= 0){
            this.coordinates.setHeight(0);
            // System.out.printf("JetPlane#%s(%d): Landing...\n", this.name, this.id);
            unregisterOutput = aircraftName + "Landing...";
            weatherTower.writeToFile("write", unregisterOutput);
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower){
        String registerOutput = "";

        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        // System.out.printf("Tower says: JetPlane#%s(%d) registered to weather tower.\n", this.name, this.id);
        registerOutput = "Tower says: JetPlane#" + this.name + "(" + this.id + ")" + "registered to weather tower.\n";
        weatherTower.writeToFile("write", registerOutput);
    }
}