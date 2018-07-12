package sources.com.avajLauncher.Simulator.Vehicles;

public class Aircraft{

    protected long              id;
    protected String            name;
    protected Coordinates       coordinates;
    private static long         idCounter = 0;

    protected Aircraft(String newName, Coordinates newCoordinates){
        name = newName;
        coordinates = newCoordinates;
        id = nextId();
    }

    private long nextId(){
        return ++idCounter;
    }

}