import java.util.HashMap;
import java.util.Map;

// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

public class Environment {

    // Used for mapping variables across different statements
    Map<String, Double> map = new HashMap<String, Double>();

    public double put(String var, double val) {
        map.put(var, val);  // Adds variable to map
        return val;
    }

    public double get(int pos, String var) throws EvalException {
        return map.get(var);   // Grabs variable from map 
    }
}
