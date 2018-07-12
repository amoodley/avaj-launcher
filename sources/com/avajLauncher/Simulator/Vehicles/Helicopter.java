package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable{

    private WeatherTower        weatherTower;

    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates);
        switch(weather){
            case "SUN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
                this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                System.out.printf("Helicopter#%s(%d): SUN: Longitude = %d, Height = %d\n", this.name, this.id, this.coordinates.getLongitude(), this.coordinates.getHeight());
                break;
            case "RAIN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
                System.out.printf("Helicopter#%s(%d): SUN: Longitude = %d\n", this.name, this.id, this.coordinates.getLongitude());
                break;
            case "FOG":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
                System.out.printf("Helicopter#%s(%d): SUN: Longitude = %d\n", this.name, this.id, this.coordinates.getLongitude());
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 12);
                System.out.printf("Helicopter#%s(%d): SUN: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                break;
        }

        if (this.coordinates.getHeight() > 100){
            this.coordinates.setHeight(100);
        }

        if (this.coordinates.getHeight() >= 0) {
            this.coordinates.setHeight(0);
            System.out.printf("Helicopter#%s(%d): Landing...\n", this.name, this.id);
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.printf("Tower says: Helicopter#%s(%d): registered to weather tower.\n", this.name, this.id);
    }
}