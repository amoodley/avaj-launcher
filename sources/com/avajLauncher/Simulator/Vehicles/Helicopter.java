package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Simulator.Vehicles.*;
import sources.com.avajLauncher.Weather.*;

public class Helicopter extends Aircraft implements Flyable{

    private WeatherTower        weatherTower;

    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates);
        String weatherOutput = "";
        String unregisterOutput = "";
        String aircraftName = "Helicopter#" + this.name + "(" + this.id + "): ";
        switch(weather){
            case "SUN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
                this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                //System.out.printf("Helicopter#%s(%d): SUN: Longitude = %d, Height = %d\n", this.name, this.id, this.coordinates.getLongitude(), this.coordinates.getHeight());
                weatherOutput = aircraftName + "This is hot. Longitude = " + this.coordinates.getLongitude() + ", Height = " + this.coordinates.getHeight() + "\n";
                break;
            case "RAIN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
                //System.out.printf("Helicopter#%s(%d): RAIN: Longitude = %d\n", this.name, this.id, this.coordinates.getLongitude());
                weatherOutput = aircraftName + "It's raining, good thing I brought an umbrella. Longitude = " + this.coordinates.getLongitude() + "\n";
                break;
            case "FOG":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
                //System.out.printf("Helicopter#%s(%d): FOG: Longitude = %d\n", this.name, this.id, this.coordinates.getLongitude());
                weatherOutput = aircraftName + "That's a lot of fog. Longitude = " + this.coordinates.getLongitude() + "\n";
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 12);
                //System.out.printf("Helicopter#%s(%d): SNOW: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                weatherOutput = aircraftName + "My rotor is going to freeze. Height = " + this.coordinates.getHeight() + "\n";
                break;
        }
        weatherTower.writeToFile("write", weatherOutput);

        if (this.coordinates.getHeight() > 100){
            this.coordinates.setHeight(100);
        }

        if (this.coordinates.getHeight() >= 0) {
            this.coordinates.setHeight(0);
            //System.out.printf("Helicopter#%s(%d): Landing...\n", this.name, this.id);
            unregisterOutput = aircraftName + "Landing...";
            weatherTower.writeToFile("write", unregisterOutput);
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower){
        String registerOutput = "";

        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        //System.out.printf("Tower says: Helicopter#%s(%d): registered to weather tower.\n", this.name, this.id);
        registerOutput = "Tower says: Helicopter#" + this.name + "(" + this.id + ")" + "registered to weather tower.\n";
        weatherTower.writeToFile("write", registerOutput);
    }
}