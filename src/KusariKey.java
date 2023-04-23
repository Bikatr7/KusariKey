import java.util.*;
import java.io.*;

import EncryptDecrypt.Encryption;
import EncryptDecrypt.Decryption;

public class KusariKey 
{
    public static void main(String[] args) throws Exception 
    {
        String masterPass;
        String userInput= "L";

        Scanner input = new Scanner(System.in);

        clearConsole();

        masterPass = logon(input);

        while(!userInput.toLowerCase().equals("q"))
        {
            System.out.println("1. View Passwords");
            System.out.println("2. Change Master Password");
            System.out.println("Q. Exit");


            userInput = input.nextLine();

            if(userInput.toLowerCase().equals("1") == true)
            {

            }
            else if(userInput.toLowerCase().equals("2") == true)
            {
                resetMasterPass();
            }

            clearConsole();

        }

        System.exit(0);


    }
    
//-------------------start-of-resetMasterPass()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void resetMasterPass()
    {

    }

//-------------------start-of-login()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static String logon(Scanner input) throws Exception
    {
        setup();

        
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");

        Scanner reader = new Scanner(masterPassFile);

        String userMasterPass = "L";
        String actualMasterPass = "W";

        while(!userMasterPass.equals(actualMasterPass))
        {
            while(userMasterPass.length() != 16)
            {
    
                System.out.println("Welcome to KusariKey, Please enter the master password to continue (Default is 123456789ABCDEFG)");

                userMasterPass = input.nextLine();

                clearConsole();

            }

            actualMasterPass = Decryption.decrypt(reader.nextLine(),userMasterPass);

            clearConsole();
        
        }

        reader.close();

        return actualMasterPass;

    }

//-------------------start-of-setup()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void setup() throws Exception
    {

        File directory = new File("C:\\ProgramData\\KusariKey\\");
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");
    
        if (!directory.exists()) 
        {
            directory.mkdir();
        }
    
        if (!masterPassFile.exists()) 
        {
            masterPassFile.createNewFile();
        }

        if(masterPassFile.length() == 0)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));

            String defaultString = "123456789ABCDEFG";

            String encryptedDefault = Encryption.encrypt(defaultString,defaultString);

            writer.write(encryptedDefault);
            writer.close();
        }
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

