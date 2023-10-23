package Modules;

// built-in libraries
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

// custom modules
import Handlers.FileHandler;

//--------------------start-of-fileEnsurer------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class FileEnsurer 
{
    private Path configDir;
    private Path credentialsDir;
    private Path credentialsFile;

    private Path logFile;

    private Logger logger;

    private FileHandler fileHandler; 

//--------------------start-of-fileEnsurer()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public FileEnsurer() throws IOException
    {

        //---------------------------------------------/

        String userHome = System.getProperty("user.home");

        configDir = Paths.get(userHome, "KusariKeyConfig");

        logFile = this.configDir.resolve("log.txt");

        //---------------------------------------------/

        try
        {
            Files.createDirectory(configDir);
        }
        catch (Exception e)
        {
        }

        try 
        {
            Files.createFile(logFile);
        } 
        catch (Exception e) 
        {
        }

        //---------------------------------------------/

        logger = new Logger(logFile);

        logger.clearLogFile();

        fileHandler = new FileHandler(logger);

        //---------------------------------------------/

        setupBaseDirectories();
    }

//--------------------start-of-getLogger()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the logger object.
     * @return Logger
     */

    public Logger getLogger()
    {
        return logger;
    }

//--------------------start-of-ensureFiles()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that all files are present and ready to be used.
     * @return void
     * @throws IOException
     */

    public void ensureFiles()  throws IOException
    {
        setupBaseDirectories();

        setupCredentialsDirectory();
    }

//--------------------start-of-setupBaseDirectories()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the base directories are present and ready to be used.
     * @return void
     * @throws IOException
     */

    private void setupBaseDirectories() throws IOException
    {

        credentialsDir = configDir.resolve("credentials");

        fileHandler.standardCreateDirectory(credentialsDir);

    }

//--------------------start-of-setupCredentialsDirectory()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the files located in the credentials directory are present and ready to be used.
     * @return void
     * @throws IOException
     */

    private void setupCredentialsDirectory() throws IOException
    {
        credentialsFile = credentialsDir.resolve("credentials.txt");

        fileHandler.standardCreateFile(credentialsFile);
    } 
    
}