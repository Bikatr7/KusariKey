package Handlers;

// custom modules
import Modules.FileEnsurer;
import Modules.Toolkit;

public class RemoteHandler 
{

 private FileEnsurer fileEnsurer;
 private Toolkit toolkit;

    public RemoteHandler() throws Exception
    {
        ConnectionHandler connectionHandler = new ConnectionHandler();
    }

}
