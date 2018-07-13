package sources.com.avajLauncher.Weather;

import sources.com.avajLauncher.Simulator.Vehicles.Flyable;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Tower{
    
    private ArrayList<Flyable>              observers = new ArrayList<Flyable>();
    private ArrayList<Flyable>              unregister = new ArrayList<Flyable>();
    private static Integer                  loopCounter = 0;

    public void register(Flyable flyable){
        try {
            observers.add(flyable);
        } catch (Exception e){
            System.out.println("Failed to register Aircraft...");
        }
    }

    public void unregister(Flyable flyable){
        try {
            unregister.add(flyable);
        } catch (Exception e){
            System.out.println("Failed to unregister Aircraft...");
        }
    }

    protected void conditionsChanged()
	{
        for (Flyable flyable : observers)
		{
            flyable.updateConditions();
        }
        observers.removeAll(unregister);
	}
}