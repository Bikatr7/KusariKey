package Modules;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileWriter;
import java.io.IOException;

//-------------------start-of-logger---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class Logger 
{

    private static String currentBatch = "";
    private static Path logPath;
    private static FileWriter logWriter;
    
    // crimes against humanity
    static 
    {
        ExceptionUtil.criticalTryCatch(() -> 
        {

        logPath = FileEnsurer.generateLogFile();
        logWriter = new FileWriter(logPath.toString(), true);
        clearLogFile();

        });
    }

//-------------------start-of-logger()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
    private Logger()
    {
        throw new UnsupportedOperationException("This is a static class and cannot be instantiated.");
    }
    
//-------------------start-of-getTimestamp()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the current timestamp for an action to be logged.
     * @return formattedDateTime String - the formatted date and time.
     */

    private static String getTimestamp()
    {

        LocalDateTime now = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String formattedDateTime = "[" + now.format(formatter) + "]";
        
        return formattedDateTime;
    }

//-------------------start-of-logAction()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Logs an action to the current batch.
     * @param action String - the action to be logged.
     */

    public static void logAction(String action)
    {
        currentBatch += getTimestamp() + " " + action + "\n";
    }

//-------------------start-of-pushBatch()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Pushes the batch to the log file.
     */

    public static void pushBatch()
    {

        ExceptionUtil.criticalTryCatch(() -> 
        {
        logWriter.write(currentBatch);
        logWriter.flush();

        });
        
        currentBatch = "";

    }

//-------------------start-of-clearLogFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Clears the log file.
     */

    public static void clearLogFile() throws IOException
    {

        logWriter.close();

        // Re-open in non-append mode (truncate file) and close
        try (FileWriter truncateWriter = new FileWriter(logPath.toString(), false)) {}

        // reopen it for usage
        logWriter = new FileWriter(logPath.toString(), true);

    }
}




