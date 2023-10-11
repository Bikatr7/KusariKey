package Handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionHandler 
{
    public connectionHandler(String URL, String USER, String PASSWORD)
    {
        try 
        {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
            connection.close();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }    
}
