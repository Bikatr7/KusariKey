import java.util.*;
import java.io.*;

import CoreModule.*;

public class KusariKey 
{
    public static void main(String[] args) throws Exception 
    {
        String masterPass;
        String userInput= "L";

        setup();

        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner reader = new Scanner(passwordFile);
        Scanner input = new Scanner(System.in);

        int lines = 0;

        while (reader.hasNextLine())
        {
            lines++;
            reader.nextLine();
        }

        Login[] passwords = new Login[lines];

        Core.clearConsole();

        masterPass = logon(input);

        getPasswords(passwords,masterPass);

        while(!userInput.toLowerCase().equals("q"))
        {
            System.out.println("1. View Passwords");
            System.out.println("2. Change Master Password");
            System.out.println("3. Add Password");
            System.out.println("Q. Exit");


            userInput = input.nextLine();

            Core.clearConsole();

            if(userInput.toLowerCase().equals("1") == true)
            {
                System.out.print("l");
                viewPasswords(input, passwords, masterPass);
                System.out.print("l");
            }
            else if(userInput.toLowerCase().equals("2") == true)
            {
                masterPass = resetMasterPass(input);
            }
            else if(userInput.toLowerCase().equals("3") == true)
            {
                addPassword("test", "test", "test", masterPass);
            }

            Core.clearConsole();

        }

        input.close();
        reader.close();

        System.exit(0);


    }
    
//-------------------start-of-resetMasterPass()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static String resetMasterPass(Scanner input) throws Exception
    {
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");

        String userMasterPass = "L";
        String actualMasterPass = "W";
        String newMasterPass = "K";

        String obfuscatedNewPass;

        while(!userMasterPass.equals(actualMasterPass))
        {
            Scanner reader = new Scanner(masterPassFile);

            while(userMasterPass.length() != 16)
            {
    
                System.out.println("Please enter the master password to reset your master password (Default is 123456789ABCDEFG)");

                userMasterPass = input.nextLine();

                Core.clearConsole();

            }
            
            try
            {
                actualMasterPass = reader.nextLine();
            }
            catch(Exception e)
            {
                userMasterPass = "";
                reader.close();
            }

            Core.clearConsole();
        
        }

        while(newMasterPass.length() != 16)
        {

            newMasterPass = Core.userConfirm("Please enter a new master password (Please note a password must be 16 characters)", input);

            Core.clearConsole();

        }

        obfuscatedNewPass = newMasterPass;

        BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));
        writer.write(obfuscatedNewPass);
        writer.close();

        redoPasswords(actualMasterPass,newMasterPass);

        return newMasterPass;
    }

//-------------------start-of-getPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void getPasswords(Login[] passwords,String masterPass) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner reader = new Scanner(passwordFile);

        for(int i = 0; i < passwords.length; i++)
        {
            passwords[i] = new Login(reader.next(), reader.next(),reader.nextLine().strip());
        }

        reader.close();
    }
//-------------------start-of-login()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static String logon(Scanner input) throws Exception
    {
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");

        String userMasterPass = "L";
        String actualMasterPass = "W";

        Scanner reader = new Scanner(masterPassFile);

        while(!userMasterPass.equals(actualMasterPass) )
        {

            while(userMasterPass.length() != 16)
            {
                System.out.println("Welcome to KusariKey, Please enter the master password to continue (Default is 123456789ABCDEFG)");

                userMasterPass = input.nextLine();

                Core.clearConsole();

            }

            try
            {
                actualMasterPass = reader.nextLine();
            } 
            catch (Exception e) 
            {
                userMasterPass = "F";
            }


            Core.clearConsole();
        
        }

        reader.close();
        
        return actualMasterPass;

    }

//-------------------start-of-viewPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void viewPasswords(Scanner input,Login[] passwords,String masterPass) throws Exception
    {

        for(int i = 0; i < passwords.length; i++)
        {
            System.out.println(passwords[i].getLoginName());
            System.out.println(passwords[i].getEmail());
            System.out.println(passwords[i].getPassword());

            System.out.println("");
        }

        input.nextLine();

    }
//-------------------start-of-redoPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void redoPasswords(String oldMasterPass, String newMasterPass) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner counter = new Scanner(passwordFile);
        Scanner reader = new Scanner(passwordFile);

        int lines = 0;
        int i = 0;

        while (counter.hasNextLine())
        {
            lines++;
            counter.nextLine();
        }

        String[] emailArray = new String[lines];
        String[] passwordArray = new String[lines];
        String[] loginNameArray = new String[lines];

        while(reader.hasNextLine())
        {
            String email = reader.next();
            String password = reader.next();
            String loginName = reader.nextLine();

            emailArray[i] = email;
            passwordArray[i] = password;
            loginNameArray[i] = loginName;

            i+=1;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile));

        i = 0;

        while(i < lines)
        {
            addPassword(emailArray[i], passwordArray[i], loginNameArray[i], newMasterPass);
            i+=1;
        }

        reader.close();
        counter.close();
        writer.close();

    }

//-------------------start-of-addPassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addPassword(String email, String password, String loginName, String masterPass) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile,true));

        writer.write(email + " " + password +  " "  + loginName + "\n");

        writer.close();

    }

//-------------------start-of-setup()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void setup() throws Exception
    {

        File directory = new File("C:\\ProgramData\\KusariKey\\");
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");
    
        if (!directory.exists()) 
        {
            directory.mkdir();
        }
    
        if (!masterPassFile.exists()) 
        {
            masterPassFile.createNewFile();
        }

        if (!passwordFile.exists()) 
        {
            passwordFile.createNewFile();
        }

        if(masterPassFile.length() == 0)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));

            String defaultString = "123456789ABCDEFG";

            String obfuscatedDefault = defaultString;

            writer.write(obfuscatedDefault);
            writer.close();
        }
    }

    
}

