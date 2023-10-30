package Modules;

// built-in libraries
import java.util.Arrays;
import java.util.Scanner;

// custom modules
import Modules.Logger;

//--------------------start-of-toolkit------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class Toolkit
{
    private Logger logger;

//-------------------start-of-Toolkit()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public Toolkit(Logger logger)
    {
        this.logger = logger;
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
    * @param input Scanner - reads console input
    * @return userInput String - the user's input
    */

    public static String userConfirm(String prompt, Scanner input)
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

    public void handleCriticalException(Exception e)
    {
        System.out.println("KusariKey will now exit");

        pauseConsole();

        exitKusariKey();
        
    }

//-------------------start-of-exitKusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Exits KusariKey.
     */

    public void exitKusariKey()
    {
        clearConsole();

        System.out.println("Exiting KusariKey...");

        this.logger.pushBatch();

        System.exit(0);
    }

//-------------------start-of-levenshtein()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Calculates the levenshtein distance between two strings.
     * @param string1 String - the first string
     * @param string2 String - the second string
     * @return distance int - the levenshtein distance between the two strings
     */

    public int levenshtein(String string1, String string2)
    {
        int string1Length;
        int string2Length;

        int[][] distance;

        string1Length = string1.length();
        string2Length = string2.length();

        // init the distance array, all 0 by default
        distance = new int[string1Length  + 1][string2Length + 1];


        for(int i = 0; i <= string1Length; i++) 
        {
            distance[i][0] = i;
        }

        for(int ii = 0; ii <= string2Length+1; ii++)
        {
            distance[0][ii] = ii;
        }

        for(int i = 1; i < string1Length + 1; i++)
        {
            for(int ii = 1; i < string2Length + 1; ii++)
            {
                int cost = 0;

                if(string1.charAt(i - 1) == string2.charAt(ii - 1))
                {
                    cost = 0;
                }
                else
                {
                    cost = 1;
                }

                distance[i][ii] = Math.min(distance[i - 1][ii] + 1, distance[i][ii - 1] + 1);

                distance[i][ii] = Math.min(distance[i][ii], distance[i - 1][ii - 1] + cost);

                if(i > 1 && ii > 1 && string1.charAt(i - 1) == string2.charAt(ii - 2) && string1.charAt(i - 2) == string2.charAt(ii - 1))
                {
                    distance[i][ii] = Math.min(distance[i][ii], distance[i - 2][ii - 2] + cost);
                }
            }
        }

        return distance[string1Length][string2Length];

    } 

} // Toolkit