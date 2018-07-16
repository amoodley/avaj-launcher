package sources.com.avajLauncher.Simulator;

import sources.com.avajLauncher.Weather.*;
import sources.com.avajLauncher.Simulator.Vehicles.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator{

    private static WeatherTower     weatherTower;
    private static List<Flyable>    flyables = new ArrayList<Flyable>();

    public static void main(String[] arg) throws IOException {
        try {
            PrintWriter writer = new PrintWriter("simulation.txt");
            writer.print("");
            writer.close();
            BufferedReader reader = new BufferedReader(new FileReader(arg[0]));
            String line = reader.readLine();
            if (line != null){
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations < 0){
                    System.out.println("Invalid simulations count: " + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null){
                    String[] arr = line.split(" ");
                    if (arr.length == 5){
                        Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                                            Integer.parseInt(line.split(" ")[4]));
                        flyables.add(flyable);
                    } else {
                        System.out.println("Invalid file format eg:(Type Name Longitude Latitude Height).");
                    }
                }
                for (Flyable flyable: flyables){
                    flyable.registerTower(weatherTower);
                }
                for (int i = 1; i <= simulations; i++){
                    String simulationOutput = "Simulation: " + i + "\n";
                    weatherTower.writeToFile("write", simulationOutput);
                    // System.out.println("Simulation: " + i);
                    weatherTower.changeWeather();
                }

            }
            System.out.println("Simulation complete: output to 'simulation.txt'");
        } catch(FileNotFoundException e){
            System.out.println("File not found: " + arg[0]);
        } catch(IOException e){
            System.out.println("File read error: " + arg[0]);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("No file specified");
        } catch(NullPointerException e){
            System.out.println("Runtime_Error Null_Reference: " + e);
        } catch(Exception e){
            System.out.println("Unrecognised symbols in file: " + e);
        }
    }
}