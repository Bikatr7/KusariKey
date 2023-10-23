package Modules;

// built-in libraries
import java.util.Arrays;

//--------------------start-of-toolkit------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class toolkit
{

//-------------------start-of-getNewID()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public int getNewID(int[] existingIDs)
    {
        int newID = 1;

        Arrays.sort(existingIDs);

        for(int id : existingIDs)
        {
            if(id < newID)
            {
                continue;
            }
            else if(id == newID)
            {
                newID++;
            }
            else
            {
                break;
            }
        }

        return newID;
    }

} // toolkit