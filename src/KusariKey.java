import java.util.*;

import Core.*;

import java.io.*;

import Entities.Login;
import Handlers.*;

public class KusariKey 
{
    public static void main(String[] args) throws Exception 
    {

        setup();

        Core.clearConsole();

        String userInput= "L";

        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt");

        Scanner reader = new Scanner(passwordFile);
        Scanner input = new Scanner(System.in);

        Login[] passwords;

        Core.clearConsole();

        logon(input);

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
                viewPasswords(input, passwords);

            }
            else if(userInput.toLowerCase().equals("2") == true)
            {
                resetMasterPass(input);
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
    /**
    * deletes a login
    * @param passwords Login[] - holds current logins
    * @param input scanner - reads input from console
    * @param id int -  id of the login whose value we are replacing
    * @return newPasswords Login[] - holds updated logins
    * @throws Exception
    */ 

    public static Login[] delete(Login[] passwords, Scanner input, int id) throws Exception
    {
        Login[] newPasswords = new Login[passwords.length - 1];
    
        int newIndex = 0;
    
        for (int i = 0; i < passwords.length; i++)
        {
            if (passwords[i].getID() != id)
            {
                newPasswords[newIndex] = passwords[i];
                newIndex++;
            }
        }
    
        return newPasswords;
    }
    
//-------------------start-of-search()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * searches for a term
    * @param input scanner - reads input from console
    * @param passwords Login[] - holds current logins
    * @param term String -  term that is being searched for
    * @return None
    * @throws Exception
    */ 

    public static void search(Scanner input, Login[] passwords, String term) throws Exception
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
  
    /**
    * replaces a value in the passwords array
    * @param passwords Login[] - holds current logins
    * @param input scanner - reads input from console
    * @param id int -  id of the login whose value we are replacing
    * @return None
    * @throws Exception
    */ 

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
    
    /**
    * resets the master password
    * @param input scanner - reads input from console
    * @return None
    * @throws Exception
    */

    public static void resetMasterPass(Scanner input) throws Exception
    {
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt"); // where the master password for KusariKey is stored

        String userMasterPass = "L";
        String actualMasterPass = "W";
        String newMasterPass = "K";

        Scanner reader = new Scanner(masterPassFile);

        while(!userMasterPass.equals(actualMasterPass))
        {
            System.out.println("Please enter the master password to reset your master password (Default is 123456789ABCDEFG)");

            userMasterPass = input.nextLine();
            actualMasterPass = reader.nextLine();
            Core.clearConsole();

            reader.close();
            reader = new Scanner(masterPassFile);
        
        }

        newMasterPass = Core.userConfirm("Please enter a new master password : ", input);

        Core.clearConsole();

        BufferedWriter writer = new BufferedWriter(new FileWriter(masterPassFile));
        writer.write(newMasterPass);
        writer.close();

        reader.close();

    }

//-------------------start-of-getPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
    * gets the updated passwords from the passwords.txt file
    * @param None
    * @return passwords Login[] - of updated passwords
    * @throws Exception
    */

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

//-------------------start-of-logon()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * logons on to KusariKey
     * @param input scanner - reads input from console
     * @throws Exception
     */

    public static void logon(Scanner input) throws Exception
    {
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt"); // where the master password for KusariKey is stored

        String userMasterPass = "L";
        String actualMasterPass = "W";

        Scanner reader = new Scanner(masterPassFile);

        while(!userMasterPass.equals(actualMasterPass))
        {
            System.out.println("Welcome to KusariKey, Please enter the master password to continue (Default is 123456789ABCDEFG)");

            userMasterPass = input.nextLine();
            actualMasterPass = reader.nextLine();

            Core.clearConsole();

            if(userMasterPass.equals("q"))
            {
                System.exit(0);
            }

            reader.close();
            reader = new Scanner(masterPassFile);
        
        }

        reader.close();
        
    }

//-------------------start-of-viewPasswords()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * prints the passwords currently in the passwords array
     * @param input scanner - reads input from console
     * @param passwords Login[] - array of the current logins
     * @return Nothing
     * @throws Exception
     */

    public static void viewPasswords(Scanner input,Login[] passwords) throws Exception
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

    /**
    * updates the passwords in the passwords.txt to match the passwords array
    * @param passwords Login[] - an array of the current 'Logins' 
    * @return Nothing
    * @throws Exception
    */

    public static void redoPasswords(Login[] passwords) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt"); // where passwords for KusariKey are stored

        Scanner reader = new Scanner(passwordFile);

        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile)); // clears the txt file
        writer.close();

        for(int i = 0; i < passwords.length; i++)
        {
            addPassword(passwords[i].getEmail(), passwords[i].getPassword(),  passwords[i].getLoginName(), passwords[i].getID());
        }

        reader.close();

    }

//-------------------start-of-addPassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
    * Adds a password to the passwords.txt document
    * @param email string - email or username 
    * @param password string - password for login
    * @param loginName string - name for the login, where the login is used
    * @param id int - tag/id for the login, used for replacing/deleting/etc
    * @return Nothing
    * @throws Exception
    */

    public static void addPassword(String email, String password, String loginName, int id) throws Exception
    {
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt"); // where passwords for KusariKey are stored
    
        BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile,true));

        writer.write(email + " " + password +  " "  + id  + " " + loginName + '\n');

        writer.close();

    }

//-------------------start-of-setup()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * Sets up files that KusariKey needs to run
    * @param Nothing
    * @return Nothing
    * @throws Exception
    */

    public static void setup() throws Exception
    {
        File directory = new File("C:\\ProgramData\\KusariKey\\"); // directory for KusariKey
        File masterPassFile = new File("C:\\ProgramData\\KusariKey\\masterPass.txt"); // where the master password for KusariKey is stored
        File passwordFile = new File("C:\\ProgramData\\KusariKey\\passwords.txt"); // where passwords for KusariKey are stored
    
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

            writer.write(defaultString);
            writer.close();
        }
    }

    
}

