package Handlers;

// built-in libraries
import java.util.Base64;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

// third-party libraries
import java.sql.*;

// custom modules
import Modules.FileEnsurer;
import Modules.Toolkit;
import Modules.FileUtil;
import Modules.ExceptionUtil;

//-------------------start-of-ConnectionHandler---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class ConnectionHandler 
{
    private Connection connection;
    private Statement statement;

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
            user = new String(Base64.getDecoder().decode(FileUtil.readSeiLine(credentialsPath, 1, 1)), StandardCharsets.UTF_8);
            password = new String(Base64.getDecoder().decode(FileUtil.readSeiLine(credentialsPath, 1, 2)), StandardCharsets.UTF_8);
            database_name = new String(Base64.getDecoder().decode(FileUtil.readSeiLine(credentialsPath, 1, 3)), StandardCharsets.UTF_8);

            // need to add the database name to the url
            url += database_name;

            connection = DriverManager.getConnection(url, user, password);
        
        } 
        // if credentials not present, get new credentials
        catch (IllegalArgumentException | NullPointerException | SQLException | IOException | IndexOutOfBoundsException e)
        {
            try 
            {
                Toolkit.clearConsole();

                System.out.println("KusariKey requires a database to store its data. Please enter the credentials for the database you would like to use.\n\n");

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
                    Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8)),
                    Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)),
                    Base64.getEncoder().encodeToString(database_name.getBytes(StandardCharsets.UTF_8))    
                };

                FileUtil.writeSeiLine(credentialsPath, credentials);
                

            } 
            catch(IllegalArgumentException | NullPointerException | SQLException | IOException | IndexOutOfBoundsException ex)
            {
                System.err.println("Error with creating connection to database. Please check your credentials");

                ExceptionUtil.handleCriticalException(ex);
            }
        }
        
        return connection;
    }
    
//-------------------start-of-executeQuery()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Executes a query
     * @param query - String The query to be executed
     * @throws SQLException
     */

    public void executeQuery(String query) throws SQLException 
    {
        if (statement == null) 
        {
            statement = connection.createStatement();
        }

        statement.execute(query);
        connection.commit();
    }

//-------------------start-of-readSingleColumnQuery()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Executes a query and returns the results
     * @param query - String The query to be executed
     * @return results - List<String> The results of the query
     * @throws SQLException
     */

    public List<String> readSingleColumnQuery(String query) throws SQLException 
    {
        List<String> results = new ArrayList<>();

        if (statement == null) 
        {
            statement = connection.createStatement();
        }

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) 
        {
            results.add(resultSet.getString(1)); // getting the first column
        }

        return results;
    }

//-------------------start-of-readMultiColumnQuery()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Executes a query and returns the results
     * @param query - String The query to be executed
     * @return resultsByColumn - List<List<String>> The results of the query
     * @throws SQLException
     */

    public List<List<String>> readMultiColumnQuery(String query) throws SQLException 
    {
        List<List<String>> resultsByColumn = new ArrayList<>();

        if (statement == null) {
            statement = connection.createStatement();
        }

        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 0; i < columnCount; i++) 
        {
            resultsByColumn.add(new ArrayList<>());
        }

        while (resultSet.next()) 
        {
            for (int i = 1; i <= columnCount; i++) 
            {
                resultsByColumn.get(i - 1).add(resultSet.getString(i));
            }
        }

        return resultsByColumn;
    }

//-------------------start-of-insertIntoTable()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Inserts data into a table
     * @param tableName - String The name of the table to insert into
     * @param data - Map<String, String> The data to be inserted
     * @throws SQLException
     */

    public void insertIntoTable(String tableName, Map<String, String> data) throws SQLException 
    {
        String columns = String.join(", ", data.keySet());
        String values = String.join(", ", data.values().stream().map(v -> "'" + v + "'").toArray(String[]::new));
        String query = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);

        executeQuery(query);
    }
 
} // end of class ConnectionHandler
