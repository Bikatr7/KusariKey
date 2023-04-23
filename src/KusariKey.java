import java.util.*;
import java.io.*;

import EncryptDecrypt.*;
import CoreModule.*;

public class KusariKey 
{
    public static void main(String[] args) throws Exception 
    {
        String masterPass;
        String userInput= "L";

        Scanner input = new Scanner(System.in);

        Core.clearConsole();

        masterPass = logon(input);

        while(!userInput.toLowerCase().equals("q"))
        {
            System.out.println("1. View Passwords");
            System.out.println("2. Change Master Password");
            System.out.println("Q. Exit");


            userInput = input.nextLine();

            Core.clearConsole();

            if(userInput.toLowerCase().equals("1") == true)
            {

            }
            else if(userInput.toLowerCase().equals("2") == true)
            {
                masterPass = resetMasterPass(input);
            }

            Core.clearConsole();

        }

        input.close();

        System.exit(0);


    }
    
//-------------------start-of-resetMasterPass()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static String resetMasterPass(Scanner input) throws Exception
    {
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");

        Scanner reader = new Scanner(masterPassFile);

        String userMasterPass = "L";
        String actualMasterPass = "W";
        String newMasterPass = "K";

        String encryptedNewPass;

        while(!userMasterPass.equals(actualMasterPass))
        {
            while(userMasterPass.length() != 16)
            {
    
                System.out.println("Please enter the master password to reset your master password (Default is 123456789ABCDEFG)");

                userMasterPass = input.nextLine();

                Core.clearConsole();

            }

            actualMasterPass = Decryption.decrypt(reader.nextLine(),userMasterPass);

            Core.clearConsole();
        
        }

        reader.close();

        while(newMasterPass.length() != 16)
        {

            newMasterPass = Core.userConfirm("Please enter a new master password (Please note a password must be 16 characters)", input);

            Core.clearConsole();

        }

        encryptedNewPass = Encryption.encrypt(newMasterPass,newMasterPass);

        BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));
        writer.write(encryptedNewPass);
        writer.close();

        return newMasterPass;
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

                Core.clearConsole();

            }

            actualMasterPass = Decryption.decrypt(reader.nextLine(),userMasterPass);

            Core.clearConsole();
        
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

        System.out.print(masterPassFile.length());

        if(masterPassFile.length() == 0)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));

            String defaultString = "123456789ABCDEFG";

            String encryptedDefault = Encryption.encrypt(defaultString,defaultString);

            writer.write(encryptedDefault);
            writer.close();
        }
    }

    
}

