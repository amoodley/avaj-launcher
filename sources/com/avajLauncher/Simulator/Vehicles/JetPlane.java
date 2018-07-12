package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable{

    private WeatherTower        weatherTower;

    public JetPlane(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.coordinates);
        switch(weather){
            case "SUN":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
                this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                System.out.printf("JetPlane#%s(%d): SUN: Latitude = %d, Height = %d\n", this.name, this.id, this.coordinates.getLatitude(), this.coordinates.getHeight());
                break;
            case "RAIN":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
                System.out.printf("JetPlane#%s(%d): RAIN: Latitude = %d\n", this.name, this.id, this.coordinates.getLatitude());
                break;
            case "FOG":
                this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
                System.out.printf("JetPlane#%s(%d): FOG: Latitude = %d\n", this.name, this.id, this.coordinates.getLatitude());
                break;
            case "SNOW":
                this.coordinates.setHeight(this.coordinates.getHeight() - 7);
                System.out.printf("JetPlane#%s(%d): SNOW: Height = %d\n", this.name, this.id, this.coordinates.getHeight());
                break;
        }

        if (this.coordinates.getHeight() > 100){
            this.coordinates.setHeight(100);
        }

        if (this.coordinates.getHeight() <= 0){
            this.coordinates.setHeight(0);
            System.out.printf("JetPlane#%s(%d): Landing...\n", this.name, this.id);
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.printf("Tower says: JetPlane#%s(%d) registered to weather tower.\n", this.name, this.id);
    }
}