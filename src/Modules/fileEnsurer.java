package Modules;

// built-in libraries
import java.util.Map;
import java.util.HashMap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

// custom modules
import Handlers.FileHandler;

//--------------------start-of-fileEnsurer------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class FileEnsurer 
{
    private Map<String, Path> pathMap = new HashMap<String, Path>();

    private Path configDir;
    private Path credentialsDir;
    private Path credentialsFile;

    private Path logFile;

    private String[] pathNames;

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

        if(!Files.exists(configDir))
            Files.createDirectory(configDir);

        if(!Files.exists(logFile))
            Files.createFile(logFile);
        

        //---------------------------------------------/

        logger = new Logger(logFile);

        logger.clearLogFile();

        fileHandler = new FileHandler(logger);

        //---------------------------------------------/
    }

//--------------------start-of-getLogger()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the logger object.
     * @return logger Logger - The logger object.
     */

    public Logger getLogger()
    {
        return logger;
    }

//--------------------start-of-getFileHandler()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the fileHandler object.
     * @return fileHandler FileHandler - The fileHandler object.
     */

    public FileHandler getFileHandler()
    {
        return fileHandler;
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

        setupPathMap();
    }

//--------------------start-of-setupPathMap()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Sets up the pathMap.
     * @return void
     */

    public void setupPathMap()
    {
        pathMap.put("configDir", configDir);
        pathMap.put("credentialsDir", credentialsDir);
        pathMap.put("credentialsFile", credentialsFile);
        pathMap.put("logFile", logFile);
    }

//--------------------start-of-getPath()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Gets the path from the pathMap.
     * @param key String - The key of the path.
     * @return path Path - The path.
     */

    public Path getPath(String key)
    {
        pathNames = new String[pathMap.size()];

        pathMap.keySet().toArray(pathNames);

        key = Toolkit.get_intended_answer(key, pathNames);

        return pathMap.get(key);
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