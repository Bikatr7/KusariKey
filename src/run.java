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
            System.out.println("Critical Exception: " + e);

            for (StackTraceElement ste : e.getStackTrace()) 
            {
                System.out.println(ste.toString());
            }
        
            if (client.getToolkit() != null) 
            {
                client.getToolkit().handleCriticalException(e);
            } 
            else 
            {
                System.out.println("Toolkit is not initialized. Cannot handle exception.");
            }
        }
        
 
        
    }    
}
