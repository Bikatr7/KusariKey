package Modules;

// built-in libraries
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

// custom modules
import Handlers.fileHandler;

//--------------------start-of-fileEnsurer------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class fileEnsurer 
{
    private Path configDir;
    private Path credentialsDir;
    private Path credentialsFile;

    private Path logFile;

    private logger logger;

    private fileHandler fileHandler; 

//--------------------start-of-fileEnsurer()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public fileEnsurer()
    {

        String userHome = System.getProperty("user.home");

        configDir = Paths.get(userHome, "KusariKeyConfig");

        logFile = this.configDir.resolve("log.txt");

        try 
        {
            logger = new logger(logFile);
        } 
        catch (IOException e) 
        {
           System.exit(0);
        }

        fileHandler = new fileHandler(logger);

        setupBaseDirectories();

    }

//--------------------start-of-ensureFiles()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that all files are present and ready to be used.
     * @return void
     * @throws IOException
     */

    public void ensureFiles() 
    {
        try
        {
            setupBaseDirectories();

            setupCredentialsDirectory();
        }
        catch(IOException e)
        {
            System.exit(0);
        }

    }

//--------------------start-of-setupBaseDirectories()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the base directories are present and ready to be used.
     * @return void
     * @throws IOException
     */

    private void setupBaseDirectories()
    {

        credentialsDir = this.configDir.resolve("credentials");

    }

//--------------------start-of-setupCredentialsDirectory()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the files located in the credentials directory are present and ready to be used.
     * @return void
     * @throws IOException
     */

    private void setupCredentialsDirectory() throws IOException
    {
        credentialsFile = this.credentialsDir.resolve("credentials.txt");

        fileHandler.standardCreateFile(credentialsFile);
    } 
    
}