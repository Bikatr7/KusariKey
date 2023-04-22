import java.util.*;
import java.io.*;

import EncryptDecrypt.Encryption;
import EncryptDecrypt.Decryption;

public class KusariKey 
{
    public static void main(String[] args) throws Exception 
    {
        String masterPass;

        masterPass = login();

    }

//-------------------start-of-addPassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addPassword()
    {

    }

//-------------------start-of-login()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static String login() throws Exception
    {
        setup();

        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");

        Scanner reader = new Scanner(masterPassFile);
        Scanner input = new Scanner(System.in);

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
        input.close();

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

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}

