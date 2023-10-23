package Modules;

// built-in libraries
import java.util.Arrays;
import java.util.Scanner;

//--------------------start-of-toolkit------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class toolkit
{

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
    * @throws Exception
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
    * @param void
    * @return void
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

} // toolkit