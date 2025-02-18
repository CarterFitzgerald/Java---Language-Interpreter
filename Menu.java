import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.Math;

public class Menu
{
    public static String fname="Task1b.txt";
    Scanner in = null;
    
    public void runIt()
    {
        String menuOpt="";
        in = new Scanner(System.in);
        do
        {
        System.out.print("\nST2-2023 Assignment 1\n");
        System.out.print("("+fname+")\n");
        System.out.print("Q - Exit/Quit\n");
        System.out.print("W - Who Am I (Task1)\n");
        System.out.print("R - Read SAIL2023 File (Task 1)\n");
        System.out.print("E - Execute SAIL2023File (Task 1)\n");
        System.out.print("E2 - Execute SAIL2023File (Task 2a)\n");
        System.out.print("E3 - Execute SAIL2023File (Task 3a)\n");
        System.out.print("S - Set File name\n");
        System.out.print("Select Option:\n");
        menuOpt = in.nextLine().trim().toUpperCase();
        if (menuOpt.compareToIgnoreCase("W") == 0) {optW();}
        if (menuOpt.compareToIgnoreCase("R") == 0) {optR();}
        if (menuOpt.compareToIgnoreCase("E") == 0 ) {optE(true,false);}
        if (menuOpt.compareToIgnoreCase("D") == 0 ) {optE(false,true);}
        if (menuOpt.compareToIgnoreCase("E2") == 0 ) {optE2();}
        if (menuOpt.compareToIgnoreCase("E3") == 0 ) {optE3();}
        if (menuOpt.compareToIgnoreCase("S") == 0 ) {optS();}
        } while (menuOpt.compareToIgnoreCase("Q")!=0); // Note the != this time
        System.out.print("\nEnding Now\n");
        System.exit(0);
    }
    
    public void optW()
    {
        System.out.println("\nAuthor: Carter Fitzgerald u3240494\n");
    }
    
    public void optR()
    {
        System.out.println("\n Option - R\n");
    }
    
    public void optE(boolean showOpt, boolean dbg)
    {
        // Creates an ArrayList to store the lines of the file
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fname));
            // Reads in each line and adds it to the ArrayList
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            
            // Counts the number of lines and non-comment lines
            int totalLines = lines.size();
            int nonCommentLines = 0;
            for (String line : lines) {
                if (!line.startsWith("//")) {
                    nonCommentLines++;
                }
            }

            // Print out the results
            System.out.println("\n Option - E\n");
            System.out.println("Reading code file " + fname);
            System.out.printf("Number Of Lines Read in is: %12d\n", totalLines);
            System.out.println("Number Of Non Comment Lines Read in is:" + nonCommentLines);
            //Calls the interpreter to interpret the lines stored in the Arraylist
            System.out.println("----------- Running code ----------");
            Interpreter interpreter = new Interpreter();
            interpreter.executeFile(lines);
            interpreter.clearVariables();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
    
    public void optE2()
    {
        // Shortcut to Run a Task 2 File
        System.out.println("\n Option - E2\n");
        fname = "Task2a.txt";
        optE(false,false);
    }
    
    public void optE3()
    {
        // Shortcut to Run a Task 3 File
        System.out.println("\n Option - E2\n");
        fname = "Task3a.txt";
        optE(false,false);
    }
    
    public void optS()
    {
        // Prints options for files which can be used
        System.out.printf("Select Option:\n");
        System.out.printf("0- enter file name:\n");
        System.out.printf("1- Task1a:\n");
        System.out.printf("2- Task1b:\n");
        System.out.printf("3- Task1c:\n");
        System.out.printf("4- Task1d:\n"); 
        System.out.printf("5- Task2a:\n"); 
        System.out.printf("6- Task2b:\n"); 
        System.out.printf("7- Task3a:\n");
        System.out.printf("8- Task3b:\n");
        System.out.printf("9- Task4a:\n");
        System.out.printf("10- Task4b:\n");
        System.out.printf("11- Task4c:\n");
        System.out.printf("12- Task5a:\n");
        System.out.printf("13- Task1Ma:\n");
        System.out.printf("14- Task1Mb:\n");
        System.out.printf("15- Task1Mc:\n");
        System.out.printf("16- Task2Ma:\n");
        System.out.printf("17- Task3Ma:\n");
        System.out.printf("18- Task3Mb:\n");
        System.out.printf("19- Task3Mc:\n");
        System.out.printf("20- Task3Md:\n");
        
        String opt = in.nextLine().trim().toUpperCase();
        //Sets chosen option to correct Filename 
        switch (opt)
        {
        case "0":
        opt0();
        break;
        case "1":
        fname="Task1a.txt";
        break;
        case "2":
        fname="Task1b.txt";
        break;
        case "3":
        fname="Task1c.txt";
        break;
        case "4":
        fname="Task1d.txt";
        break;
        case "5":
        fname="Task2a.txt";
        break;
        case "6":
        fname="Task2b.txt";
        break;
        case "7":
        fname="Task3a.txt";
        break;
        case "8":
        fname="Task3b.txt";
        break;
        case "9":
        fname="Task4a.txt";
        break;
        case "10":
        fname="Task4b.txt";
        break;
        case "11":
        fname="Task4c.txt";
        break;
        case "12":
        fname="Task5a.txt";
        break;
        case "13":
        fname="Task1Ma.txt";
        break;
        case "14":
        fname="Task1Mb.txt";
        break;
        case "15":
        fname="Task1Mc.txt";
        break;
        case "16":
        fname="Task2Ma.txt";
        break;
        case "17":
        fname="Task3Ma.txt";
        break;
        case "18":
        fname="Task3Mb.txt";
        break;
        case "19":
        fname="Task3Mc.txt";
        break;
        case "20":
        fname="Task3Md.txt";
        break;
        }
    }
    
    public void opt0()
    {
        //Sets File Name
        fname = "";
        while (fname.compareTo("")==0)
        {
            System.out.printf("0- enter file name:\n");
            fname = in.nextLine().trim();
        }
        System.out.print("file set to "+fname);
    }
}
