package Handlers;

import java.nio.file.Path;

import com.google.protobuf.InvalidProtocolBufferException;

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

//-------------------start-of-getCurrentFilePath()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current file path.
     * @return currentFIlePath Path - The current file path.
     */

    public Path getCurrentFilePath()
    {
        return currentFilePath;
    }

//-------------------start-of-standardCreateDirectory()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a directory if it doesn't exist. Also logs the action.
     * @param directoryPath Path - The path of the directory to create.
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

//-------------------start-of-modifiedCreateFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a file if it doesn't exist or if the file is blank. Also logs the action.
     * @param filePath
     * @throws IOException
     */

    public void modifiedCreateFile(Path filePath) throws IOException
    {
        if(!filePath.toFile().exists() || filePath.toFile().length() == 0)
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


//-------------------start-of-writeSeiLine()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Writes a line to the current file.
     * Current path is switched back to the previous file after the line is written.
     * @param file_path Path - The path of the file to write to.
     * @param items_to_write E[] - The items to write to the file.
     * @throws IOException
     */

    public <E> void writeSeiLine(Path file_path, E[] items_to_write) throws IOException
    {
        String line_to_write = "";

        for(E item : items_to_write)
        {
            line_to_write += item.toString() + ",";
        }


    }

}