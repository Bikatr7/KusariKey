package CoreModule;

import java.io.*;
import java.util.*;

public class Core 
{

//--------------------Start-of-getNewID()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static int getNewID(int [] idList)
    {
        
        int expectedNum = 0;
    
        for(int i = 0; i < idList.length; i++)
        {
            if(idList[i] < expectedNum)
            {
                continue;
            }
            else if(idList[i] == expectedNum)
            {
                expectedNum += 1;
            }
            else
            {
                return expectedNum;
            }
            
        }

        return expectedNum;
    }

//-------------------start-of-userConfirm()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
