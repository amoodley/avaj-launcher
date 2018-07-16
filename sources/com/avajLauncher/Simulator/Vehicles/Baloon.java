package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Simulator.Vehicles.*;
import sources.com.avajLauncher.Weather.*;

public class Baloon extends Aircraft implements Flyable{

    private WeatherTower        weatherTower;

    public Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates);
        String weatherOutput = "";
        String unregisterOutput = "";
        String aircraftName = "Baloon#" + this.name + "(" + this.id + "): ";
        switch(weather) {
            case "SUN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
                this.coordinates.setHeight(this.coordinates.getHeight() + 4);
                // System.out.printf("Baloon#%s(%d): SUN: Longitude = %d, Height = %d\n", this.name, this.id, this.coordinates.getLongitude(), this.coordinates.getHeight());
                weatherOutput = aircraftName + "Let's enjoy the good weather and take some pics. Longitude = " + this.coordinates.getLongitude() + ", Height = " + this.coordinates.getHeight() + "\n";
                break;
            case "RAIN":
                this.coordinates.setHeight(this.coordinates.getHeight() - 5);
                // System.out.printf("Baloon#%s(%d): RAIN: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                weatherOutput = aircraftName + "Damn you rain! You messed up my baloon. Height = " + this.coordinates.getHeight() + "\n";
                break;
            case "FOG":
                this.coordinates.setHeight(this.coordinates.getHeight() - 3);
                // System.out.printf("Baloon#%s(%d): FOG: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                weatherOutput = aircraftName + "Damn this fog! I can't see a thing. Height = " + this.coordinates.getHeight() + "\n";
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 15);
                // System.out.printf("Baloon#%s(%d): SNOW: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                weatherOutput = aircraftName + "It's snowing! We're gonna crash. Height = " + this.coordinates.getHeight() + "\n";
                break;
        }
        weatherTower.writeToFile("write", weatherOutput);

        if (this.coordinates.getHeight() > 100){
            this.coordinates.setHeight(100);
        }

        if (this.coordinates.getHeight() <= 0){
            this.coordinates.setHeight(0);
            // System.out.printf("Baloon#%s(%d): Landing... \n", this.name, this.id);
            unregisterOutput = aircraftName + "Landing...";
            weatherTower.writeToFile("write", unregisterOutput);
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower){
        String registerOutput = "";

        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        // System.out.printf("Tower says: Baloon#%s(%d) registered to weather tower.\n", this.name, this.id);
        registerOutput = "Tower says: Baloon#" + this.name + "(" + this.id + ")" + "registered to weather tower.\n";
        weatherTower.writeToFile("write", registerOutput);
    }
}