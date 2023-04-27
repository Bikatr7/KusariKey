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

        Login[] passwords;

        Core.clearConsole();

        masterPass = logon(input);

        while(!userInput.toLowerCase().equals("q"))
        {
            System.out.println("1. View Passwords");
            System.out.println("2. Change Master Password");
            System.out.println("3. Add Password");
            System.out.println("4. Search");
            System.out.println("5. Replace Value");
            System.out.println("6. Delete Password");
            System.out.println("Q. Exit");

            userInput = input.nextLine();

            Core.clearConsole();

            passwords = getPasswords();

            if(userInput.toLowerCase().equals("1") == true)
            {
                viewPasswords(input, passwords, masterPass);

            }
            else if(userInput.toLowerCase().equals("2") == true)
            {
                masterPass = resetMasterPass(input,passwords);
                redoPasswords(passwords);
            }
            else if(userInput.toLowerCase().equals("3") == true)
            {
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

                addPassword(email, password, loginName, newID);
            }

            else if(userInput.toLowerCase().equals("4") == true)
            {
                System.out.print("Please enter search term : ");
                String term = input.nextLine();
            
                search(input, passwords,term);
            }
            else if(userInput.toLowerCase().equals("5") == true)
            {
                System.out.print("Please enter id : ");
                int id = input.nextInt();

                replace(passwords, input, id);
                redoPasswords(passwords);
                passwords = getPasswords();
            }
            else if(userInput.toLowerCase().equals("6") == true)
            {
                System.out.print("Please enter id : ");
                int id = input.nextInt();

                passwords = delete(passwords, input, id);
                redoPasswords(passwords);
                passwords = getPasswords();
            }

            Core.clearConsole();

        }

        input.close();
        reader.close();

        System.exit(0);
        
    }

//-------------------start-of-delete()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static Login[] delete(Login[] passwords, Scanner input, int id) throws Exception
    {
        Login[] newPasswords = new Login[passwords.length-1];

        for(int i = 0; i < passwords.length; i++)
        {
            if(passwords[i].getID() != id)
            {
                newPasswords[i] = passwords[i];
            }
        }

        return newPasswords;
    }


//-------------------start-of-search()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void search(Scanner input, Login[] passwords, String term)
    {
        Core.clearConsole();

        boolean matched = false;

        for(int i = 0; i < passwords.length; i++)
        {
            if(passwords[i].getEmail().contains(term) || passwords[i].getLoginName().contains(term) || passwords[i].getPassword().contains(term) || String.valueOf(passwords[i].getID()).contains(term))
            {
                System.out.println("-------------------------------------------\n");

                System.out.println("Login Name : " + passwords[i].getLoginName());
                System.out.println("Username : " + passwords[i].getEmail());
                System.out.println("Password : " + passwords[i].getPassword());
                System.out.println("ID: " + passwords[i].getID());

                System.out.println("");

                matched = true;
            }
        }

        if (!matched) {
            System.out.println("No matches found.");
        }

        input.nextLine();
    }

//-------------------start-of-replace()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void replace(Login[] passwords, Scanner input, int id) throws Exception
    {
        int loc = -1;

        for (int i = 0; i < passwords.length; i++) 
        {
         if(passwords[i].getID() == id)
         {
            System.out.println("-------------------------------------------\n");

            System.out.println("1. Login Name : " + passwords[i].getLoginName());
            System.out.println("2. Username : " + passwords[i].getEmail());
            System.out.println("3. Password : " + passwords[i].getPassword());
            System.out.println("4. ID: " + passwords[i].getID());

            System.out.println("");

            loc = i;
         }   

        }

        if(loc == -1)
        {
            return;
        }

        System.out.print("\nWhat are you replacing (1-4) : ");
        int choice = input.nextInt();
        input.nextLine();

        System.out.print("\nWhat are you replacing it with : ");
        String term = input.nextLine();
        Core.clearConsole();


        if(choice == 1)
        {
            passwords[loc].changeLoginName(term);
        }
        else if(choice == 2)
        {
            passwords[loc].changeEmail(term);
        }
        else if(choice == 3)
        {
            passwords[loc].changePassword(term);
        }
        else if(choice == 4)
        {
            passwords[loc].changeID(Integer.parseInt(term));
        }
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

        return newMasterPass;
    }

//-------------------start-of-getPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Login[] getPasswords() throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner reader = new Scanner(passwordFile);

        int lines = 0;

        while (reader.hasNextLine())
        {
            lines++;
            reader.nextLine();
        }

        reader.close();
        reader = new Scanner(passwordFile);

        Login[] passwords = new Login[lines];

        for(int i = 0; i < passwords.length; i++)
        {
            passwords[i] = new Login(reader.next(), reader.next(), reader.nextInt(), reader.nextLine().strip());
        }

        reader.close();

        return passwords;
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

                if(userMasterPass.equals("q"))
                {
                    System.exit(0);
                }

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

    public static void redoPasswords(Login[] passwords) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner reader = new Scanner(passwordFile);

        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile));
        writer.close();

        for(int i = 0; i < passwords.length; i++)
        {
            addPassword(passwords[i].getEmail(), passwords[i].getPassword(),  passwords[i].getLoginName(), passwords[i].getID());
        }

        reader.close();

    }


//-------------------start-of-addPassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addPassword(String email, String password, String loginName, int id) throws Exception
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

