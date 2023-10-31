package Base;

// custom modules
import Modules.FileEnsurer;
import Modules.Toolkit;

import Handlers.RemoteHandler;

//-------------------start-of-KusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class KusariKey 
{
    private FileEnsurer fileEnsurer;

    private Toolkit toolkit;

    private RemoteHandler remoteHandler;

//-------------------start-of-KusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public KusariKey()
    {

    }

//-------------------start-of-getToolkit()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the toolkit.
     * @return toolkit Toolkit - the toolkit
     */

    public Toolkit getToolkit()
    {
        return toolkit;
    }

//-------------------start-of-runKusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void runKusariKey() throws Exception 
    {
        setup();

        Toolkit.exitKusariKey();
    }

//-------------------start-of-setup()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void setup() throws Exception
    {
        fileEnsurer = new FileEnsurer();

        toolkit = new Toolkit(fileEnsurer.getLogger());
        
        fileEnsurer.ensureFiles();

        remoteHandler = new RemoteHandler(fileEnsurer, toolkit);

    }
}
