package Modules;

// built-in libraries
import java.util.Arrays;
import java.util.Scanner;

// custom modules
import Modules.Logger;

//--------------------start-of-toolkit------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class Toolkit
{
    private static Logger logger;
    private Scanner input;

//-------------------start-of-Toolkit()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public Toolkit(Logger logger)
    {
        this.logger = logger;
        this.input = new Scanner(System.in);
    }

//-------------------start-of-getInput()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the input scanner.
     * @return input Scanner - the input scanner
     */

    public Scanner getInput()
    {
        return this.input;
    }

//-------------------start-of-getNewID()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Generates a new ID for a new object.
     * @param existingIDs int[] - the IDs of the existing objects
     * @return newID int - the new ID
     */

    public int getNewID(int[] existingIDs)
    {
        int newID = 1;

        Arrays.sort(existingIDs);

        for(int id : existingIDs)
        {
            if(id < newID)
            {
                continue;
            }
            else if(id == newID)
            {
                newID++;
            }
            else
            {
                break;
            }
        }

        return newID;
    }

//-------------------start-of-userConfirm()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
    * Confirms user input.
    * @param prompt String - the message we are prompting the user with
    * @return userInput String - the user's input
    */

    public String userConfirm(String prompt)
    {
        String confirmation = "Just To Confirm You Selected ";
        String options = " Press 1 To Confirm or 2 To Retry";

        String output = "";
        String userInput = "";
        String confirmInput = "";

        Boolean entryConfirmed = false;

        while(!entryConfirmed)
        {
            System.out.println(prompt);

            userInput = input.nextLine();

            if(userInput.equals("q"))
            {
                System.exit(0);
            }

            clearConsole();

            output = confirmation + userInput + options;

            System.out.println(output);

            confirmInput = input.nextLine();

            if(confirmInput.equals("1"))
            {
                entryConfirmed = true;
            }
            else
            {
                clearConsole();

                System.out.println(prompt);
            }

        }

        return userInput;
    }

    
//-------------------start-of-clearConsole()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
    /**
    * Clears the console.
    */

    public static void clearConsole() 
    {
        try 
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (Exception e)
        {
            // skip
        }
    }

//-------------------start-of-pauseConsole()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Pauses the console.
     */

    public static void pauseConsole()
    {
        try 
        {
            new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
        } 
        catch (Exception e)
        {
            // skip
        }
    }

//-------------------start-of-handleCriticalException()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Handles a critical exception.
     * @param e Exception - the exception we are handling
     */

    public static void handleCriticalException(Exception e)
    {
        System.out.println("Critical Exception: " + e.getMessage());

        for (StackTraceElement ste : e.getStackTrace()) 
        {
            System.out.println(ste.toString());
        }

        System.out.println("KusariKey will now exit");

        pauseConsole();

        exitKusariKey();
        
    }

//-------------------start-of-exitKusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Exits KusariKey.
     */

    public static void exitKusariKey()
    {
        clearConsole();

        System.out.println("Exiting KusariKey...");

        logger.pushBatch();

        System.exit(0);
    }

//-------------------start-of-levenshtein()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Calculates the levenshtein distance between two strings.
     * @param string1 String - the first string
     * @param string2 String - the second string
     * @return distance int - the levenshtein distance between the two strings
     */

     public static int levenshtein(String string1, String string2) 
     {
        int string1Length = string1.length();
        int string2Length = string2.length();
    
        int[][] distance = new int[string1Length + 1][string2Length + 1];
    
        for (int i = 0; i <= string1Length; i++) 
        {
            distance[i][0] = i;
        }
    
        for (int ii = 0; ii <= string2Length; ii++) 
        {
            distance[0][ii] = ii;
        }
    
        for (int i = 1; i <= string1Length; i++) 
        {
            for (int ii = 1; ii <= string2Length; ii++) 
            {
                int cost = (string1.charAt(i - 1) == string2.charAt(ii - 1)) ? 0 : 1;
    
                distance[i][ii] = Math.min(distance[i - 1][ii] + 1, // deletion
                                 Math.min(distance[i][ii - 1] + 1, // insertion
                                          distance[i - 1][ii - 1] + cost)); // substitution
    
                if (i > 1 && ii > 1 && string1.charAt(i - 1) == string2.charAt(ii - 2) && string1.charAt(i - 2) == string2.charAt(ii - 1)) {
                    
                    distance[i][ii] = Math.min(distance[i][ii], distance[i - 2][ii - 2] + cost); // transposition
                }
            }
        }
    
        return distance[string1Length][string2Length];
    }
    

//-------------------start-of-get_intended_answer()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the intended answer.
     * @param typo String - the typo
     * @param correct_answers String[] - the correct answers
     * @return closest_string String - the closest string
     */

    public static String get_intended_answer(String typo, String[] correct_answers) 
    {
        int closest_distance = Integer.MAX_VALUE;
        String closest_string = "";

        for (String string : correct_answers) 
        {
            int distance = levenshtein(typo, string);
            
            if (distance < closest_distance) 
            {
                closest_distance = distance;
                closest_string = string;
            }
        }

        return closest_string;
    }

} // Toolkit