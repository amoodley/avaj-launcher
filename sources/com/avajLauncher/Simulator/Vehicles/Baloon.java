package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Weather.WeatherTower;


public class Baloon extends Aircraft implements Flyable{

    private WeatherTower        weatherTower;

    public Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates);
        switch(weather) {
            case "SUN":
                this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
                this.coordinates.setHeight(this.coordinates.getHeight() + 4);
                System.out.printf("Baloon#%s(%d): SUN: Longitude = %d, Height = %d\n", this.name, this.id, this.coordinates.getLongitude(), this.coordinates.getHeight());
                break;
            case "RAIN":
                this.coordinates.setHeight(this.coordinates.getHeight() - 5);
                System.out.printf("Baloon#%s(%d): RAIN: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                break;
            case "FOG":
                this.coordinates.setHeight(this.coordinates.getHeight() - 3);
                System.out.printf("Baloon#%s(%d): FOG: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 15);
                System.out.printf("Baloon#%s(%d): SNOW: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                break;
            
        }

        if (this.coordinates.getHeight() > 100){
            this.coordinates.setHeight(100);
        }

        if (this.coordinates.getHeight() <= 0){
            this.coordinates.setHeight(0);
            System.out.printf("Baloon#%s(%d): Landing... \n", this.name, this.id);
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.printf("Tower says: Baloon#%s(%d) registered to weather tower.\n", this.name, this.id);
    }
}