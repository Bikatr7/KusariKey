package Handlers;

// third-party libraries
import java.sql.SQLException;

//-------------------start-of-RemoteHandler---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class RemoteHandler 
{

    private ConnectionHandler connectionHandler;

//-------------------start-of-RemoteHandler()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public RemoteHandler() throws ClassNotFoundException, SQLException
    {
        connectionHandler = new ConnectionHandler();
    }

//-------------------start-of-ensureDatabase()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the database exists and is ready to be used
     * @throws SQLException
     */

    public void ensureDatabase() throws SQLException
    {

   
    }

//-------------------start-of-ensureTables()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the tables exist and are ready to be used
     * @throws SQLException
     */
    public void ensureTables() throws SQLException
    {
        String createEntryCredentialsTableQuery="""
                create table if not exists entry_credentials
                (
                    entry_id int not null,
                    entry_password varchar(255) not null,
                    primary key (entry_id)
                );
                """;

        connectionHandler.executeQuery(createEntryCredentialsTableQuery);
    }

}
