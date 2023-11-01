package Base;

// custom modules
import Modules.ExceptionUtil;
import Modules.FileEnsurer;
import Modules.Toolkit;

import Handlers.RemoteHandler;

//-------------------start-of-KusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class KusariKey 
{
    private RemoteHandler remoteHandler;

//-------------------start-of-KusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public KusariKey()
    {

    }
//-------------------start-of-runKusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Runs the KusariKey program.
     * @throws Exception
     */

    public void runKusariKey() throws Exception 
    {
        setup();

        ExceptionUtil.exitKusariKey();
    }

//-------------------start-of-setup()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Sets up the KusariKey program.
     * @throws Exception
     */

    private void setup() throws Exception
    {
        FileEnsurer.ensureFiles();

        remoteHandler = new RemoteHandler();
    }
}
