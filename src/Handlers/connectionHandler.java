package Handlers;

// built-in libraries
import java.util.Base64;
import java.util.Scanner;

import java.io.IOException;
import java.nio.file.Path;

// third-party libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Modules.ExceptionUtil;
// custom modules
import Modules.FileEnsurer;
import Modules.Toolkit;
import Modules.FileUtil;

//-------------------start-of-ConnectionHandler---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class ConnectionHandler 
{
    private Connection connection;

//-------------------start-of-ConnectionHandler()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public ConnectionHandler() throws ClassNotFoundException, SQLException
    {
        this.connection = initializeDatabaseConnection();
    }
    
//-------------------start-of-initializeDatabaseConnection()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Initializes the database connection
     * @return connection - Connection The database connection
     */

    private Connection initializeDatabaseConnection() throws ClassNotFoundException, SQLException
    {
        String user;
        String password;
        String database_name;

        String url = "jdbc:mysql://localhost:3306/";

        String[] credentials;

        Connection connection = null;

        Path credentialsPath = FileEnsurer.getPath("credentialsFile");
        
        Scanner input = Toolkit.getInput();

        // mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        try 
        {
            // get the credentials from the credentials file if it exists
            user = new String(Base64.getDecoder().decode(FileUtil.readSeiLine(credentialsPath, 1, 0)));
            password = new String(Base64.getDecoder().decode(FileUtil.readSeiLine(credentialsPath, 1, 1)));
            database_name = new String(Base64.getDecoder().decode(FileUtil.readSeiLine(credentialsPath, 1, 2)));

            // need to add the database name to the url
            url += database_name;

            connection = DriverManager.getConnection(url, user, password);
        
        } 
        // if credentials not present, get new credentials
        catch (IllegalArgumentException | NullPointerException | SQLException |IOException e)
        {
            try 
            {
                Toolkit.clearConsole();

                System.out.print("Please enter the name of the database you are connecting to: ");
                database_name = input.nextLine();

                Toolkit.clearConsole();

                System.out.printf("Please enter the username you are using to connect to %s: ", database_name);
                user = input.nextLine();

                Toolkit.clearConsole();

                System.out.printf("Please enter the password you are using to connect to %s: ", database_name);
                password = input.nextLine();

                Toolkit.clearConsole();

                connection = DriverManager.getConnection(url, user, password);

                // write the credentials to the credentials file
                credentials = new String[] {
                    Base64.getEncoder().encodeToString(user.getBytes()),
                    Base64.getEncoder().encodeToString(password.getBytes()),
                    Base64.getEncoder().encodeToString(database_name.getBytes())
                };

                FileUtil.writeSeiLine(credentialsPath, credentials);
                
                
            } 
            catch(IllegalArgumentException | NullPointerException | SQLException | IOException ex)
            {
                System.err.println("Error with creating connection to database. Please check your credentials");

                ExceptionUtil.handleCriticalException(ex);
            }
        }
        
        return connection;
    }
}
