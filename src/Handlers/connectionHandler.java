package Handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler 
{

    private Connection connection;

    public ConnectionHandler()
    {
        this.connection = initializeDatabaseConnection();
    }
    
    
    private Connection initializeDatabaseConnection()
    {
        Connection fauxConnection = null;
        
        return fauxConnection;
    }
}
