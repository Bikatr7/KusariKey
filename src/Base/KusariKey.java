package Base;
// Assuming the classes are in the following packages
import Modules.FileEnsurer;
import Modules.Toolkit;

import Handlers.RemoteHandler;

//-------------------start-of-KusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class KusariKey 
{
    private static FileEnsurer fileEnsurer;

    private static Toolkit toolkit;

    private static RemoteHandler remoteHandler;

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

        toolkit.exitKusariKey();
    }

//-------------------start-of-setup()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private static void setup() throws Exception
    {
        fileEnsurer = new FileEnsurer();

        toolkit = new Toolkit(fileEnsurer.getLogger());
        
        fileEnsurer.ensureFiles();

        remoteHandler = new RemoteHandler(fileEnsurer, toolkit);

    }
}
