package Modules;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileWriter;
import java.io.IOException;

//-------------------start-of-logger---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class Logger 
{

    private String batch;
    private Path logPath;
    private FileWriter logWriter;

//-------------------start-of-logger()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
    public Logger(Path logPath) throws IOException
    {
        
        this.batch = "";
        this.logPath = logPath;

        this.logWriter = new FileWriter(logPath.toString(), true);
        
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

//-------------------start-of-clearLogFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Clears the log file
     * @param void
     * @return None
     */

    public void clearLogFile()
    {
        try
        {
            logWriter.close();

            // Re-open in non-append mode (truncate file) and close
            try (FileWriter truncateWriter = new FileWriter(logPath.toString(), false)) {}

            // reopen it for usage
            logWriter = new FileWriter(logPath.toString(), true);
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }
}




