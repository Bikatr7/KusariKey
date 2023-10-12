package Core;

import java.io.*;
import java.util.*;

public class Core 
{

//--------------------Start-of-getNewID()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
    /**
    * Gets a new id for a new login
    * @param idList int[] - holds all the existing ids
    * @return newID int - the new id returned
    * @throws Exception
    */

    public static int getNewID(int [] idList)
    {
        Arrays.sort(idList);
        
        int newID = 1;
    
        for(int i = 0; i < idList.length; i++)
        {
            if(idList[i] < newID)
            {
                continue;
            }
            else if(idList[i] == newID)
            {
                newID += 1;
            }
            else
            {
                return newID;
            }
            
        }

        return newID;
    }

//-------------------start-of-userConfirm()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
    * Confirms user input
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
    * clears the console
    * @param  None
    * @return None
    */

    public static void clearConsole() 
    {
        try 
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (IOException | InterruptedException ex) 
        {
            // skip
        }
    }

}
