package sources.com.avajLauncher.Weather;

import sources.com.avajLauncher.Simulator.Vehicles.Flyable;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Tower{
    
    private ArrayList<Flyable>              observers = new ArrayList<Flyable>();
    private ArrayList<Flyable>              unregister = new ArrayList<Flyable>();
    private static Integer                  loopCounter = 0;
    private File                            file;
    private FileWriter                      writer;

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
    
    public void writeToFile(String state, String text){
        try {
            this.file = new File("simulation.txt");
            this.writer = new FileWriter(file, true);
            this.file.createNewFile();
            
            switch(state){
                case "write":
                    try {
                        writer.write(text);
                        writer.flush();
                    } catch(Exception e){
                        System.out.println("Error: Could not write to file.");
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: Could not open file.");
        }
    }
}