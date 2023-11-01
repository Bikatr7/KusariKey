// custom modules
import Base.KusariKey;
import Modules.ExceptionUtil;

public class Run 
{
    public static void main(String[] args)
    {

        KusariKey client = new KusariKey();

        // run the client, catching any critical exceptions
        ExceptionUtil.criticalTryCatch(() -> 
        {

        client.runKusariKey();

        });

    }    
}
