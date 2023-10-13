package Handlers;

import java.nio.file.Path;

import java.io.FileWriter;
import java.io.IOException;

import Modules.logger;

//-------------------start-of-fileHandler---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class fileHandler 
{
    private Path currentFilePath;
    private FileWriter writer;

    private logger logger;

//-------------------start-of-fileHandler()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public fileHandler(logger logger)
    {
        this.logger = logger;
        
    }

//-------------------start-of-standardCreateDirectory()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a directory if it doesn't exist. Also logs the action.
     * @param directoryPath
     * @throws IOException
     */

    public void standardCreateDirectory(Path directoryPath) throws IOException
    {
        if(!directoryPath.toFile().exists())
        {
            directoryPath.toFile().mkdirs();
            logger.logAction(directoryPath.toString() + " was created due to the lack of it's existence.");
        }
    }


//-------------------start-of-standardCreateFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a file if it doesn't exist. Also logs the action.
     * @param filePath
     * @throws IOException
     */

    public void standardCreateFile(Path filePath) throws IOException
    {
        if(!filePath.toFile().exists())
        {
            filePath.toFile().createNewFile();
            logger.logAction(filePath.toString() + " was created due to the lack of it's existence.");
        }
    }

//-------------------start-of-switchToFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Switches the current file to the file specified by the filePath parameter.
     * @param filePath
     * @throws IOException
     */

    public void switchToFile(Path filePath) throws IOException
    {
        currentFilePath = filePath;

        if(writer != null)
        {
            writer.close();
        }

        writer = new FileWriter(currentFilePath.toString(), true);
        logger.logAction("Switched to file: " + currentFilePath.toString());
    }
}
