package Handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionHandler 
{

    private Connection connection;

    public connectionHandler()
    {
        this.connection = initializeDatabaseConnection();
    }
    
    
    private Connection initializeDatabaseConnection()
    {
        Connection fauxConnection = null;
        

        return fauxConnection;
    }
}
