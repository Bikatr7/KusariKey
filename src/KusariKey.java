import java.util.*;
import java.io.*;

import CoreModule.*;

public class KusariKey 
{
    public static void main(String[] args) throws Exception 
    {

        setup();

        Core.clearConsole();

        String masterPass;
        String userInput= "L";

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

        while(!userInput.toLowerCase().equals("q"))
        {
            System.out.println("1. View Passwords");
            System.out.println("2. Change Master Password");
            System.out.println("3. Add Password");
            System.out.println("4. Search");
            System.out.println("Q. Exit");

            userInput = input.nextLine();

            Core.clearConsole();

            if(userInput.toLowerCase().equals("1") == true)
            {
                getPasswords(passwords,masterPass);
                viewPasswords(input, passwords, masterPass);

            }
            else if(userInput.toLowerCase().equals("2") == true)
            {
                getPasswords(passwords,masterPass);
                masterPass = resetMasterPass(input,passwords);
            }
            else if(userInput.toLowerCase().equals("3") == true)
            {
                getPasswords(passwords,masterPass);

                int[] idList = new int[passwords.length];
    
                System.out.print("Email/Username : ");
                String email = input.nextLine();
                Core.clearConsole();

                System.out.print("Password : ");
                String password = input.nextLine();
                Core.clearConsole();
                
                System.out.print("Login Name : ");
                String loginName = input.nextLine();
                Core.clearConsole();

                for(int i = 0; i < passwords.length; i++)
                {
                    idList[i] = passwords[i].getID();
                }

                int newID = Core.getNewID(idList);

                addPassword(email, password, loginName, newID, masterPass);
            }

            else if(userInput.toLowerCase().equals("4") == true)
            {
                getPasswords(passwords,masterPass);
                search(input, passwords);
            }

            Core.clearConsole();

        }

        input.close();
        reader.close();

        System.exit(0);
        
    }


//-------------------start-of-search()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public static void search(Scanner input, Login[] passwords)
{
    System.out.print("Please enter search term : ");
    String term = input.nextLine();

    Core.clearConsole();

    for(int i = 0; i < passwords.length; i++)
    {
        if(passwords[i].getEmail().equals(term) || passwords[i].getLoginName().equals(term) || passwords[i].getPassword().equals(term) || String.valueOf(passwords[i].getID()).equals(term))
        {
            System.out.println("-------------------------------------------\n");

            System.out.println("Login Name : " + passwords[i].getLoginName());
            System.out.println("Username : " + passwords[i].getEmail());
            System.out.println("Password : " + passwords[i].getPassword());
            System.out.println("ID: " + passwords[i].getID());

            System.out.println("");
        }
    }

    input.nextLine();
}    

//-------------------start-of-resetMasterPass()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static String resetMasterPass(Scanner input, Login[] passwords) throws Exception
    {
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt");

        String userMasterPass = "L";
        String actualMasterPass = "W";
        String newMasterPass = "K";

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));
        writer.write(newMasterPass);
        writer.close();

        redoPasswords(actualMasterPass,newMasterPass,passwords);

        return newMasterPass;
    }

//-------------------start-of-getPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void getPasswords(Login[] passwords,String masterPass) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner reader = new Scanner(passwordFile);

        for(int i = 0; i < passwords.length; i++)
        {
            passwords[i] = new Login(reader.next(), reader.next(), reader.nextInt(), reader.nextLine().strip());
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
            System.out.println("-------------------------------------------\n");

            System.out.println("Login Name : " + passwords[i].getLoginName());
            System.out.println("Username : " + passwords[i].getEmail());
            System.out.println("Password : " + passwords[i].getPassword());
            System.out.println("ID: " + passwords[i].getID());

            System.out.println("");
        }

        input.nextLine();

    }
//-------------------start-of-redoPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void redoPasswords(String oldMasterPass, String newMasterPass,Login[] passwords) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile));
        writer.close();

        for(int i = 0; i < passwords.length; i++)
        {
            addPassword(passwords[i].getEmail(), passwords[i].getPassword(),  passwords[i].getLoginName(), passwords[i].getID(), newMasterPass);
        }

    }

//-------------------start-of-addPassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addPassword(String email, String password, String loginName, int id, String masterPass) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile,true));

        writer.write(email + " " + password +  " "  + id  + " " + loginName + '\n');

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

