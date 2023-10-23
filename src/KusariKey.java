// Assuming the classes are in the following packages
import Modules.FileEnsurer;
import Handlers.RemoteHandler;

public class KusariKey 
{
    // Class member declaration
    private static FileEnsurer fileEnsurer;
    private static RemoteHandler remoteHandler;

    public static void main(String[] args) throws Exception 
    {

        setup();
    }

    private static void setup() throws Exception
    {
        fileEnsurer = new FileEnsurer();
        
        remoteHandler = new RemoteHandler();
    }
}
