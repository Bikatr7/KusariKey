package Handlers;

// built-in libraries
import java.util.List;

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

//-------------------start-of-getConnectionHandler()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the connection handler
     * @return
     */

    public ConnectionHandler getConnectionHandler()
    {
        return connectionHandler;
    }

//-------------------start-of-ensureDatabase()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the database exists and is ready to be used
     * @throws SQLException
     */

    public void ensureDatabase() throws SQLException
    {

    }

    public void getUserLoggedIn () throws SQLException
    {

        // make sure the credentials table exists
        ensureCredentialsTable();

        // check if empty
        List<String> result = connectionHandler.readSingleColumnQuery("select count(*) from entry_credentials");

        if(result.get(0).equals("0"))
        {
            // create a new user

        }
        else
        {
            // get the user
        }

    }

//-------------------start-of-ensureCredentialsTables()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the credentials tables exist and are ready to be used
     * @throws SQLException
     */
    public void ensureCredentialsTable() throws SQLException
    {
        String createEntryCredentialsTableQuery="""
                create table if not exists entry_credentials
                (
                    entry_id int not null,
                    entry_password_hash varchar(255) not null,
                    entry_salt varchar(255) not null,
                    primary key (entry_id)
                );
                """;

        connectionHandler.executeQuery(createEntryCredentialsTableQuery);
    }

}
