package sources.com.avajLauncher.Simulator.Vehicles;

import sources.com.avajLauncher.Simulator.Vehicles.Helicopter;
import sources.com.avajLauncher.Simulator.Vehicles.Baloon;
import sources.com.avajLauncher.Simulator.Vehicles.JetPlane;
import sources.com.avajLauncher.Simulator.Vehicles.Coordinates;

public class AircraftFactory{
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height){
        
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        Helicopter  helicopter;
        JetPlane    jetPlane;
        Baloon      baloon;

        switch(type){
            case "JetPlane":
                 return jetPlane = new JetPlane(name, coordinates);
            case "Helicopter":
                return helicopter = new Helicopter(name, coordinates);
            case "Baloon":
                return baloon = new Baloon(name, coordinates);
            default:
                System.out.printf("Aircraft type does not exist...");
                return null;
        }
    }
}