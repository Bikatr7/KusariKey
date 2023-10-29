package Handlers;

// third-party libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// custom modules
import Modules.FileEnsurer;
import Modules.Toolkit;

//-------------------start-of-ConnectionHandler---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class ConnectionHandler 
{

    private Connection connection;
    private FileEnsurer fileEnsurer;
    private Toolkit toolkit;

//-------------------start-of-ConnectionHandler()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public ConnectionHandler(FileEnsurer fileEnsurer, Toolkit toolkit)
    {
        this.fileEnsurer = fileEnsurer;
        this.toolkit = toolkit;
    
        this.connection = initializeDatabaseConnection();
    }
    
//-------------------start-of-initializeDatabaseConnection()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Initializes the database connection
     * @return connection - Connection The database connection
     */

    private Connection initializeDatabaseConnection()
    {
        Connection fauxConnection = null;
        
        return fauxConnection;
    }
}
