package Modules;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileWriter;
import java.io.IOException;

//-------------------start-of-logger---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class logger 
{

    private String batch;
    private FileWriter logWriter;

//-------------------start-of-logger()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
    public logger(Path logPath) throws IOException
    {
        
        batch = "";

        logWriter = new FileWriter(logPath.toString(), true);
        
    }

//-------------------start-of-getTimestamp()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the current timestamp for an action to be logged
     * @param void
     * @return formattedDateTime - String
     */

    private String getTimestamp()
    {

        LocalDateTime now = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String formattedDateTime = "[" + now.format(formatter) + "]";
        
        return formattedDateTime;
    }

//-------------------start-of-logAction()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Logs an action to the batch
     * @param action - String
     * @return None
     */

    public void logAction(String action)
    {
        batch += getTimestamp() + " " + action + "\n";
    }

//-------------------start-of-pushBatch()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Pushes the batch to the log file
     * @param void
     * @return None
     */

    public void pushBatch()
    {

        try
        {
            logWriter.write(batch);
            logWriter.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }

        batch = "";

    }
}




