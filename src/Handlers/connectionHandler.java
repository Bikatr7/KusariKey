package Handlers;

// third-party libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// custom modules
import Modules.FileEnsurer;
import Modules.Toolkit;

public class ConnectionHandler 
{

    private Connection connection;
    private FileEnsurer fileEnsurer;
    private Toolkit toolkit;

    public ConnectionHandler(FileEnsurer fileEnsurer, Toolkit toolkit)
    {
        this.fileEnsurer = fileEnsurer;
        this.toolkit = toolkit;
    
        this.connection = initializeDatabaseConnection();
    }
    
    
    private Connection initializeDatabaseConnection()
    {
        Connection fauxConnection = null;
        
        return fauxConnection;
    }
}
