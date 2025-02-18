import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.Math;

public class Statement {
    //Creates tree map to store variables
    private Map<String, Integer> variables;
    
    //Stores variables from any previous code/files
    public Statement(Map<String, Integer> variables) {
        this.variables = variables;
    }
    
    //This is what the token array gets passed into
    public void execute(String[] tokens) {
        //Checks for blank line
        if (tokens.length == 0) {
            return;
        }
        //This creates a variable called command from the first token in the
        //array and uses that to determine which method to run for this execution
        String command = tokens[0];
        //Switch case to call the method which corresponds with command value
        switch (command) {
            case "set":
                set(tokens);
                break;
            case "add":
                add(tokens);
                break;
            case "subtract":
                subtract(tokens);
                break;
            case "multiply":
                multiply(tokens);
                break;
            case "divide":
                divide(tokens);
                break;
            case "print":
                print(tokens);
                break;
            case "println":
                println(tokens);
                break;
            case "end":
                end();
                break;
            default:
                //Skips over comments and deals with unknown commands
                if (command.startsWith("//")) {
                    break;
                } else {
                    throw new IllegalArgumentException("Unknown command: " + tokens[0]);
                }
        }
    }
    
    //This method sets the variables in the file code and stores them in the tree map
    private void set(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length != 4 || !tokens[2].equals("to")) {
            throw new IllegalArgumentException("Invalid syntax for set command: " + String.join(" ", tokens));
        }
        //Setting key name for storage.
        String varName = tokens[1];
        //Below attemps to convert token[3] into an integer
        int value;
        try {
            value = Integer.parseInt(tokens[3]);
        } catch (NumberFormatException e) {
            if (variables.containsKey(tokens[3])) {
                value = variables.get(tokens[3]);
            } else {
                throw new IllegalArgumentException("Invalid value for set command: " + tokens[3]);
            }
        }
        //Stores variable name as key and integer as value as pair in tree map
        variables.put(varName, value);
    }

    private void add(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length != 4 || !tokens[2].equals("to")) {
            throw new IllegalArgumentException("Invalid syntax for add command: " + String.join(" ", tokens));
        }
        String var1Name = tokens[1];
        int var1Value;
        //Trys to parse first variable or literal as an integer
        try {
            var1Value = Integer.parseInt(var1Name);
        } catch (NumberFormatException e) {
            Integer value = variables.get(var1Name);
            if (value == null) {
                throw new IllegalArgumentException("Undefined Variable: " + var1Name);
            }
            var1Value = value;
        }
        //Gets integer from second variable
        String var2Name = tokens[3];
        Integer var2Value = variables.get(var2Name);
        //Checks the second variable has a value set
        if (var2Value == null) {
            throw new IllegalArgumentException("Undefined Variable: " + var2Name);
        }
        //Performs Addition
        int result = var1Value + var2Value;
        //Updates the second variable with result from addition
        variables.put(var2Name, result);
    }

    private void subtract(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length != 4 || !tokens[2].equals("from")) {
            throw new IllegalArgumentException("Invalid syntax for subtract command: " + String.join(" ", tokens));
        }
        String var1Name = tokens[1];
        int var1Value;
        //Trys to parse first variable or literal as an integer
        try {
            var1Value = Integer.parseInt(var1Name);
        } catch (NumberFormatException e) {
            Integer value = variables.get(var1Name);
            if (value == null) {
                throw new IllegalArgumentException("Undefined Variable: " + var1Name);
            }
            var1Value = value;
        }
        //Gets integer from second variable
        String var2Name = tokens[3];
        Integer var2Value = variables.get(var2Name);
        //Checks the second variable has a value set
        if (var2Value == null) {
            throw new IllegalArgumentException("Undefined Variable: " + var2Name);
        }
        //Performs Subtraction
        int result = var2Value - var1Value;
        //Updates the second variable with result from subtraction
        variables.put(var2Name, result);
    }
    
    //Works the same as addition but switches which variable gets checked for literals
    private void multiply(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length != 4 || !tokens[2].equals("by")) {
            throw new IllegalArgumentException("Invalid syntax for multiply command: " + String.join(" ", tokens));
        }
        String var1Name = tokens[1];
        Integer var1Value = variables.get(var1Name);
        if (var1Value == null) {
            throw new IllegalArgumentException("Undefined Variable: " + var1Name);
        }
        String var2Name = tokens[3];
        int var2Value;
        try {
            var2Value = Integer.parseInt(var2Name);
        } catch (NumberFormatException e) {
            Integer value = variables.get(var2Name);
            if (value == null) {
                throw new IllegalArgumentException("Undefined Variable: " + var2Name);
            }
            var2Value = value;
        }
        int result = var1Value * var2Value;
        variables.put(var1Name, result);
    }

    //Works the same as addition but switches which variable gets checked for literals
    private void divide(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length != 4 || !tokens[2].equals("by")) {
            throw new IllegalArgumentException("Invalid syntax for divide command: " + String.join(" ", tokens));
        }
        String var1Name = tokens[1];
        Integer var1Value = variables.get(var1Name);
        if (var1Value == null) {
            throw new IllegalArgumentException("Undefined Variable: " + var1Name);
        }
        String var2Name = tokens[3];
        int var2Value;
        try {
            var2Value = Integer.parseInt(var2Name);
        } catch (NumberFormatException e) {
            Integer value = variables.get(var2Name);
            if (value == null) {
                throw new IllegalArgumentException("Undefined Variable: " + var2Name);
            }
            var2Value = value;
        }
        if (var2Value == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        int result = var1Value / var2Value;
        variables.put(var1Name, result);
    }

    private void print(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length < 2) {
            throw new IllegalArgumentException("Invalid syntax for println command: " + String.join(" ", tokens));
        }
        // This string builder reconstructs the string to be printed through the token values
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            stringBuilder.append(tokens[i]).append(" ");
        }
        String value = stringBuilder.toString().trim();
        //Handles if print cls is called and what part is to be printed using ''
        // as well as handling printing variables and integers
        if (value.equals("cls")) {
            // Clear the screen
            System.out.print("\f");
        } else if (value.startsWith("'") && value.endsWith("'")) {
            // string literal enclosed in single quotes
            System.out.print(value.substring(1, value.length() - 1));
        } else {
            // variable or integer
            try {
                int intValue = Integer.parseInt(value);
                System.out.print(intValue);
            } catch (NumberFormatException e) {
                Integer variableValue = variables.get(value);
                if (variableValue == null) {
                    throw new IllegalArgumentException("Variable empty: " + value);
                }
                System.out.print(variableValue);  
            }
        }
    }

    private void println(String[] tokens) {
        //Checks that the line in file has correct syntax
        if (tokens.length < 2) {
            throw new IllegalArgumentException("Invalid syntax for println command: " + String.join(" ", tokens));
        }
        // This string builder reconstructs the string to be printed through the token values
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            stringBuilder.append(tokens[i]).append(" ");
        }
        String value = stringBuilder.toString().trim();
        //Handles if print cls is called and what part is to be printed using ''
        // as well as handling printing variables and integers
        if (value.equals("cls")) {
            // Clear the screen
            System.out.print("\f");
        } else if (value.startsWith("'") && value.endsWith("'")) {
            // string literal enclosed in single quotes
            System.out.println(value.substring(1, value.length() - 1));
        } else {
            // variable or integer
            try {
                int intValue = Integer.parseInt(value);
                System.out.println(intValue);
            } catch (NumberFormatException e) {
                Integer variableValue = variables.get(value);
                if (variableValue == null) {
                    throw new IllegalArgumentException("Unknown variable: " + value);
                }
                System.out.println(variableValue);
            }
        }
    }
    
    private int getVariableValue(String variable) {
        //Returns the value from related key in tree map
        if (variables.containsKey(variable)) {
            return variables.get(variable);
        } else {
            return Integer.parseInt(variable);
        }
    }
    
    private void end() {
        //Recalls the Menu when program reads that the code in file has ended
        Menu menu = new Menu();
        menu.runIt();
    }
}