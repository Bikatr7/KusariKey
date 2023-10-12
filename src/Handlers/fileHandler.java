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
