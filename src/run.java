// custom modules
import Base.KusariKey;
import Modules.Toolkit;

public class Run 
{
    public static void main(String[] args)
    {

        KusariKey client = new KusariKey();

        try 
        {
            client.runKusariKey();
        } 
        catch (Exception e)  
        {
            Toolkit.handleCriticalException(e);
        }
        
 
        
    }    
}
