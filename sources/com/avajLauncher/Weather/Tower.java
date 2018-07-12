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

    protected void conditionsChanged(){
//        writeToFile("write", "\n\n");
        // if (this.observers.size() <= 0){
        //     System.out.println("Change conditions");
        //     // writeToFile("write", "\n\n All Aircrafts have landed.");
        //     // writeToFile("destroy", "");
        //     System.exit(0);
        // }

        // System.out.println("Change conditions: + size: "  +  this.observers.size());
        // this.observers.removeAll(unregister);
        // for (Integer loopCount = 0; loopCount < this.observers.size(); loopCount++){
        //     System.out.println("Loop conditions: + size: "  +  loopCount);
        //     this.observers.get(loopCount).updateConditions();

        // }
        for (Flyable var : observers) {
            System.out.println(var.updateConditions());
            var.updateConditions();
        }
        observers.removeAll(unregister);

    }
}