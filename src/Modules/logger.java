package Modules;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//-------------------start-of-logger---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class logger 
{

    private String batch;
    private Path logPath;

//-------------------start-of-logger()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
    public logger(Path logPath)
    {
        this.logPath = logPath;
        this.batch = "";
        
    }

//-------------------start-of-getTimestamp()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the current timestamp for an action to be logged
     * @param None
     * @return formattedDateTime - String
     */

    private String getTimestamp()
    {

        LocalDateTime now = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String formattedDateTime = "[" + now.format(formatter) + "]";
        
        return formattedDateTime;
    }
}




