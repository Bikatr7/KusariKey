package Modules;

// built-in libraries
import java.util.Map;
import java.util.HashMap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

//--------------------start-of-fileEnsurer------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class FileEnsurer 
{
    private static Map<String, Path> pathMap = new HashMap<String, Path>();

    private static Path configDir;
    private static Path credentialsDir;
    private static Path credentialsFile;

    private static Path logFile;

    private static String[] pathNames;

//--------------------start-of-fileEnsurer()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private FileEnsurer()
    {
        throw new UnsupportedOperationException("This is a static class and cannot be instantiated.");
    }

//--------------------start-of-generateLogFile()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Path generateLogFile() throws IOException
    {

        logFile = configDir.resolve("log.txt");

        if(!Files.exists(logFile))
            Files.createFile(logFile);

        Logger.clearLogFile();

        return logFile;
    }

//--------------------start-of-ensureFiles()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that all files are present and ready to be used.
     * @throws IOException
     */

    public static void ensureFiles()  throws IOException
    {

        configDir = Paths.get(System.getProperty("user.home"), "KusariKeyConfig");

        if(!Files.exists(configDir))
            Files.createDirectory(configDir);

        setupBaseDirectories();

        setupCredentialsDirectory();

        setupPathMap();
    }

//--------------------start-of-setupPathMap()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Sets up the pathMap.
     */

    public static void setupPathMap()
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

    public static Path getPath(String key)
    {
        pathNames = new String[pathMap.size()];

        pathMap.keySet().toArray(pathNames);

        key = Toolkit.get_intended_answer(key, pathNames);

        return pathMap.get(key);
    }

//--------------------start-of-setupBaseDirectories()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the base directories are present and ready to be used.
     * @throws IOException
     */

    private static void setupBaseDirectories() throws IOException
    {
        credentialsDir = configDir.resolve("credentials");

        FileUtil.standardCreateDirectory(credentialsDir);
    }

//--------------------start-of-setupCredentialsDirectory()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Ensures that the files located in the credentials directory are present and ready to be used.
     * @throws IOException
     */

    private static void setupCredentialsDirectory() throws IOException
    {
        credentialsFile = credentialsDir.resolve("credentials.txt");

        FileUtil.standardCreateFile(credentialsFile);
    } 
    
}