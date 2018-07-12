find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java sources.com.avajLauncher.Simulator.Simulator scenario.txt