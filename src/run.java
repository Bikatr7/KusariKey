import Base.KusariKey;

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
            client.getToolkit().handleCriticalException(e);
        }
 
        
    }    
}
