import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.Math;

public class Interpreter {
    private Map<String, Integer> variables;
    
    public Interpreter() {
        //Creates a tree map called variables to store the key-value pairs for 
        //variables in the SAIL2023 Language files
        variables = new TreeMap<>();
    }
    
    public void executeFile(ArrayList<String> lines) {
        //Provides variables stored in tree map to Statement class
        Statement statement = new Statement(variables);
        /*Loops through every line stored in the Arraylist and splits that line 
        into an array of tokens, then calls the exectute method from statement
        class onto the array list of tokens*/
        for (String line : lines) {
            String[] tokens = line.split(" ");
            statement.execute(tokens);
        }
    }
    
    public void clearVariables() {
        // Clears pairs stored in tree map, for running new code
        variables.clear();
    }
}