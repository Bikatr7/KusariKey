package Handlers;

// custom modules
import Handlers.ConnectionHandler;

import Modules.FileEnsurer;
import Modules.Toolkit;

public class RemoteHandler 
{

 private FileEnsurer fileEnsurer;
 private Toolkit toolkit;

    public RemoteHandler(FileEnsurer fileEnsurer, Toolkit toolkit)
    {

        this.fileEnsurer = fileEnsurer;
        this.toolkit = toolkit;

        ConnectionHandler connectionHandler = new ConnectionHandler(fileEnsurer, toolkit);

    }

}
